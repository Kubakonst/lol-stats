package pl.noip.lolstats.lol.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.noip.lolstats.lol.stats.dto.auth.LoginResponse;
import pl.noip.lolstats.lol.stats.dto.auth.RegistrationRequest;
import pl.noip.lolstats.lol.stats.dto.error.ErrorResponse;
import pl.noip.lolstats.lol.stats.jwt.BearerTokenSeparator;
import pl.noip.lolstats.lol.stats.jwt.JwtChecker;
import pl.noip.lolstats.lol.stats.jwt.JwtGenerator;
import pl.noip.lolstats.lol.stats.model.Account;
import pl.noip.lolstats.lol.stats.repository.AccountRepository;
import pl.noip.lolstats.lol.stats.utils.Sha;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private JwtGenerator jwtGenerator;
    private AccountRepository accountRepository;
    private JwtChecker jwtChecker;
    private BearerTokenSeparator bearerTokenSeparator;

    public AuthController(JwtGenerator jwtGenerator, AccountRepository accountRepository, JwtChecker jwtChecker, BearerTokenSeparator bearerTokenSeparator) {
        this.jwtGenerator = jwtGenerator;
        this.accountRepository = accountRepository;
        this.jwtChecker = jwtChecker;
        this.bearerTokenSeparator = bearerTokenSeparator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody RegistrationRequest registrationRequest) {
        Account account = accountRepository.findOne(registrationRequest.getEmail());
        String passHash = Sha.hash(registrationRequest.getPassword());
        if (account != null && passHash.equals(account.getPasswordHash())) {
            String token = jwtGenerator.generate(account);
            return new ResponseEntity<>(new LoginResponse(token, "bearer " + token), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse("invalid email or password"), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
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

    @PostMapping("/checkToken")
    public void checkToken(@RequestHeader(required = false, value = "Authorization") String bearer) {
        String token = bearerTokenSeparator.getToken(bearer);
        jwtChecker.checkToken(token);
    }
}