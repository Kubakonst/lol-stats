package pl.noip.lolstats.lol.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;
import pl.noip.lolstats.lol.stats.dto.SummonerDataResponse;
import pl.noip.lolstats.lol.stats.jwt.JwtGenerator;
import pl.noip.lolstats.lol.stats.jwt.JwtParser;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;
import pl.noip.lolstats.lol.stats.repository.AccountRepository;
import pl.noip.lolstats.lol.stats.service.RiotDataClient;

@RestController
@RequestMapping("/api/summoner/riotData")
@Slf4j
public class RiotDataController {

    private JwtParser jwtParser;

    private TokenSplit tokenSplit;

    private RiotDataClient riotDataClient;

    public RiotDataController(RiotDataClient riotDataClient, JwtParser jwtParser, TokenSplit tokenSplit) {
        this.riotDataClient = riotDataClient;
        this.jwtParser = jwtParser;
        this.tokenSplit = tokenSplit;
    }

    @PostMapping
    public SummonerDataResponse data(@RequestHeader(value = "Authorization") String bearer) {

        if (bearer == null) {
            throw new BearerNotPresentException();
        }

        String oldToken = tokenSplit.splitToken(bearer);

        String name = jwtParser.getName(oldToken);
        log.info("name recived from token");
        String region = jwtParser.getRegion(oldToken);
        log.info("region recived from token");
        return riotDataClient.getSummonerData(name, region);
}
}