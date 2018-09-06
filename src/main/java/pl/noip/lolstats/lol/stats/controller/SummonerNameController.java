package pl.noip.lolstats.lol.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.noip.lolstats.lol.stats.dto.auth.LoginResponse;
import pl.noip.lolstats.lol.stats.dto.stats.SummonerNameRequest;
import pl.noip.lolstats.lol.stats.jwt.JwtGenerator;
import pl.noip.lolstats.lol.stats.jwt.JwtInfoProvider;
import pl.noip.lolstats.lol.stats.model.Account;
import pl.noip.lolstats.lol.stats.model.JwtInfo;
import pl.noip.lolstats.lol.stats.repository.AccountRepository;
import pl.noip.lolstats.lol.stats.service.RiotRestClient;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/summoner/name")
@Slf4j
public class SummonerNameController {

    private JwtInfoProvider jwtInfoProvider;
    private JwtGenerator jwtGenerator;
    private AccountRepository accountRepository;
    private RiotRestClient riotRestClient;

    public SummonerNameController(JwtInfoProvider jwtInfoProvider, JwtGenerator jwtGenerator, AccountRepository accountRepository, RiotRestClient riotRestClient) {
        this.jwtInfoProvider = jwtInfoProvider;
        this.jwtGenerator = jwtGenerator;
        this.accountRepository = accountRepository;
        this.riotRestClient = riotRestClient;
    }

    @PostMapping
    public ResponseEntity<?> name(@RequestBody @Valid SummonerNameRequest summonerNameRequest,
                                  @RequestHeader(value = "Authorization") String bearer) {
        JwtInfo jwtInfo = jwtInfoProvider.fromBearerToken(bearer);
        Account account = accountRepository.findOne(jwtInfo.getEmail());
        account.setSumName(summonerNameRequest.getSumName());
        account.setRegion(summonerNameRequest.getRegion());
        account.setId(riotRestClient.getSummonerData(summonerNameRequest.getSumName(), summonerNameRequest.getRegion()).getId());
        account.setAccountId(riotRestClient.getSummonerData(summonerNameRequest.getSumName(), summonerNameRequest.getRegion()).getAccountId());
        String token = jwtGenerator.generate(account);
        return new ResponseEntity<>(new LoginResponse(token, "bearer " + token), HttpStatus.OK);
    }
}