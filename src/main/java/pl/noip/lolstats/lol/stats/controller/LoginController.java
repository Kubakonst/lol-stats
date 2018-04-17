package pl.noip.lolstats.lol.stats.controller;

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

@RestController
@RequestMapping("/api/auth/login")
public class LoginController {

    private JwtGenerator jwtGenerator;

    private AccountRepository accountRepository;

    public LoginController(JwtGenerator jwtGenerator, AccountRepository accountRepository) {
        this.jwtGenerator = jwtGenerator;
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public ResponseEntity<?> Login(@RequestBody RegistrationRequest registrationRequest) {

        Account account = accountRepository.findOne(registrationRequest.getEmail());

        if (account == null) {
            return new ResponseEntity<>(new ErrorResponse("invalid email or password"), HttpStatus.UNAUTHORIZED);
        }
        String passHash = Sha.hash(registrationRequest.getPassword());
        if (passHash.equals(account.getPasswordHash())) {
            String token = jwtGenerator.generate();
            return new ResponseEntity<>(new LoginResponse(token, "bearer " + token), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("invalid email or password"), HttpStatus.UNAUTHORIZED);
    }
}
