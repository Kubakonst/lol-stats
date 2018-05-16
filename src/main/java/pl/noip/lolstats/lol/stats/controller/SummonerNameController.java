package pl.noip.lolstats.lol.stats.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;
import pl.noip.lolstats.lol.stats.dto.LoginResponse;
import pl.noip.lolstats.lol.stats.dto.RiotNameRequest;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;
import pl.noip.lolstats.lol.stats.jwt.JwtGenerator;
import pl.noip.lolstats.lol.stats.jwt.JwtParser;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;
import pl.noip.lolstats.lol.stats.model.Account;
import pl.noip.lolstats.lol.stats.repository.AccountRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/summoner/Name")
public class SummonerNameController {

    private JwtGenerator jwtGenerator;

    private AccountRepository accountRepository;

    private JwtParser jwtParser;

    private TokenSplit tokenSplit;

    public SummonerNameController(JwtGenerator jwtGenerator, AccountRepository accountRepository, JwtParser jwtParser, TokenSplit tokenSplit) {
        this.jwtGenerator = jwtGenerator;
        this.accountRepository = accountRepository;
        this.jwtParser = jwtParser;
        this.tokenSplit = tokenSplit;
    }

    @PostMapping
    public ResponseEntity<?> name(@RequestBody @Valid SummonerNameRequest summonerNameRequest, RiotNameRequest riotNameRequest,
                                  @RequestHeader(value = "Authorization") String bearer) {

        if (bearer == null) {
            throw new BearerNotPresentException();
        }

        String oldToken = tokenSplit.splitToken(bearer);

        String mail = jwtParser.getMail(oldToken);

        Account account = accountRepository.findOne(mail);

        account.setSumName(summonerNameRequest.getSumName());

        String token = jwtGenerator.generate((mail), summonerNameRequest.getSumName(), riotNameRequest.getRegion());

        return new ResponseEntity<>(new LoginResponse(token, "bearer " + token), HttpStatus.OK);
    }
}