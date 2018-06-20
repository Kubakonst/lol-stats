package pl.noip.lolstats.lol.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;
import pl.noip.lolstats.lol.stats.dto.LoginResponse;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;
import pl.noip.lolstats.lol.stats.jwt.JwtGenerator;
import pl.noip.lolstats.lol.stats.jwt.JwtParser;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;
import pl.noip.lolstats.lol.stats.model.Account;
import pl.noip.lolstats.lol.stats.repository.AccountRepository;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/summoner/data")
@Slf4j
public class SummonerDataController {

    private JwtGenerator jwtGenerator;

    private AccountRepository accountRepository;

    private JwtParser jwtParser;

    private TokenSplit tokenSplit;

    public SummonerDataController(JwtGenerator jwtGenerator, AccountRepository accountRepository, JwtParser jwtParser, TokenSplit tokenSplit) {
        this.jwtGenerator = jwtGenerator;
        this.accountRepository = accountRepository;
        this.jwtParser = jwtParser;
        this.tokenSplit = tokenSplit;
    }

    @PostMapping
    public ResponseEntity<?> name(@RequestBody @Valid SummonerNameRequest summonerNameRequest,
                                  @RequestHeader(value = "Authorization") String bearer) {

        if (bearer == null) {
            throw new BearerNotPresentException();
        }

        String oldToken = tokenSplit.splitToken(bearer);

        String mail = jwtParser.getMail(oldToken);

        Account account = accountRepository.findOne(mail);

        String token = jwtGenerator.generate(account);

        return new ResponseEntity<>(new LoginResponse(token, "bearer " + token), HttpStatus.OK);
    }
}