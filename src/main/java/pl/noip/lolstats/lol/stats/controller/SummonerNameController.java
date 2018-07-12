package pl.noip.lolstats.lol.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.noip.lolstats.lol.stats.dto.LoginResponse;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;
import pl.noip.lolstats.lol.stats.jwt.JwtGenerator;
import pl.noip.lolstats.lol.stats.jwt.JwtParser;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;
import pl.noip.lolstats.lol.stats.model.Account;
import pl.noip.lolstats.lol.stats.repository.AccountRepository;
import pl.noip.lolstats.lol.stats.service.RiotRestClient;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/summoner/name")
@Slf4j
public class SummonerNameController {

    private JwtGenerator jwtGenerator;

    private AccountRepository accountRepository;

    private JwtParser jwtParser;

    private TokenSplit tokenSplit;

    private RiotRestClient riotRestClient;

    public SummonerNameController(JwtGenerator jwtGenerator, AccountRepository accountRepository, JwtParser jwtParser, TokenSplit tokenSplit, RiotRestClient riotRestClient) {
        this.jwtGenerator = jwtGenerator;
        this.accountRepository = accountRepository;
        this.jwtParser = jwtParser;
        this.tokenSplit = tokenSplit;
        this.riotRestClient = riotRestClient;
    }

    @PostMapping
    public ResponseEntity<?> name(@RequestBody @Valid SummonerNameRequest summonerNameRequest,
                                  @RequestHeader(value = "Authorization") String bearer) {

        String oldToken = tokenSplit.splitToken(bearer);

        String mail = jwtParser.getMail(oldToken);

        Account account = accountRepository.findOne(mail);

        account.setSumName(summonerNameRequest.getSumName());
        log.info("user summoner name added to database");
        account.setRegion(summonerNameRequest.getRegion());
        log.info("user region added to database");
        account.setId(riotRestClient.getSummonerData(summonerNameRequest.getSumName(), summonerNameRequest.getRegion()).getId());
        log.info("user summoner id added to database");
        account.setAccountId(riotRestClient.getSummonerData(summonerNameRequest.getSumName(), summonerNameRequest.getRegion()).getAccountId());
        log.info("user account id added to database");
        String token = jwtGenerator.generate(account);

        return new ResponseEntity<>(new LoginResponse(token, "bearer " + token), HttpStatus.OK);
    }
}