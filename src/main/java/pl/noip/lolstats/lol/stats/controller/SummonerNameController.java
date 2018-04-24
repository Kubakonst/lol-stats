package pl.noip.lolstats.lol.stats.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.dto.LoginResponse;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;
import pl.noip.lolstats.lol.stats.jwt.JwtGenerator;
import pl.noip.lolstats.lol.stats.jwt.JwtGetMail;
import pl.noip.lolstats.lol.stats.model.Account;
import pl.noip.lolstats.lol.stats.repository.AccountRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/summoner/name")
public class SummonerNameController {

    private JwtGenerator jwtGenerator;

    private AccountRepository accountRepository;

    private JwtGetMail jwtGetMail;

    public SummonerNameController(JwtGenerator jwtGenerator, AccountRepository accountRepository, JwtGetMail jwtGetMail) {
        this.jwtGenerator = jwtGenerator;
        this.accountRepository = accountRepository;
        this.jwtGetMail = jwtGetMail;
    }

    @PostMapping
    public ResponseEntity<?> name(@RequestBody @Valid SummonerNameRequest summonerNameRequest) {

        accountRepository.save(new Account(summonerNameRequest.getSumName()));

        String token = jwtGenerator.generate(summonerNameRequest.getSumName());

        return new ResponseEntity<>(new LoginResponse(token, "bearer " + token), HttpStatus.OK);
    }


}


