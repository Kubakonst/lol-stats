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
@RequestMapping("/api/auth/register")
@Slf4j
public class RegistrationController {

    private JwtGenerator jwtGenerator;

    private AccountRepository accountRepository;

    public RegistrationController(AccountRepository accountRepository, JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest registrationReguest) {
        if (!accountRepository.exists(registrationReguest.getEmail())) {
            accountRepository.save(Account.builder()
                    .email(registrationReguest.getEmail())
                    .passwordHash(Sha.hash(registrationReguest.getPassword()))
                    .build()
            );
            Account account = accountRepository.findOne(registrationReguest.getEmail());
            log.info("user created in database");
            String token = jwtGenerator.generate(account);
            return new ResponseEntity<>(new LoginResponse(token, "bearer " + token), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("email already exists"), HttpStatus.BAD_REQUEST);
    }
}