package pl.noip.lolstats.lol.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.dto.ErrorResponse;
import pl.noip.lolstats.lol.stats.dto.LoginResponse;
import pl.noip.lolstats.lol.stats.dto.RegistrationRequest;
import pl.noip.lolstats.lol.stats.jwt.JwtGenerator;
import pl.noip.lolstats.lol.stats.model.Account;
import pl.noip.lolstats.lol.stats.repository.AccountRepository;
import pl.noip.lolstats.lol.stats.utils.Sha;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/login")
@Slf4j
public class LoginController {

    private JwtGenerator jwtGenerator;

    private AccountRepository accountRepository;

    public LoginController(JwtGenerator jwtGenerator, AccountRepository accountRepository) {
        this.jwtGenerator = jwtGenerator;
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody RegistrationRequest registrationRequest) {

        Account account = accountRepository.findOne(registrationRequest.getEmail());

        String passHash = Sha.hash(registrationRequest.getPassword());
        if (account != null && passHash.equals(account.getPasswordHash())) {
            log.info("correct password");
            String token = jwtGenerator.generate(account);
            return new ResponseEntity<>(new LoginResponse(token, "bearer " + token), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("invalid email or password"), HttpStatus.UNAUTHORIZED);
    }
}
