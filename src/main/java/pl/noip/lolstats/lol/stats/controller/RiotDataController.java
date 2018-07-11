package pl.noip.lolstats.lol.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;
import pl.noip.lolstats.lol.stats.dto.SummonerDataResponse;
import pl.noip.lolstats.lol.stats.jwt.JwtParser;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;
import pl.noip.lolstats.lol.stats.service.RiotRestClient;

@RestController
@RequestMapping("/api/summoner/basicInfo")
@Slf4j
public class RiotDataController {

    private JwtParser jwtParser;

    private TokenSplit tokenSplit;

    private RiotRestClient riotRestClient;

    public RiotDataController(RiotRestClient riotRestClient, JwtParser jwtParser, TokenSplit tokenSplit) {
        this.riotRestClient = riotRestClient;
        this.jwtParser = jwtParser;
        this.tokenSplit = tokenSplit;
    }

    @PostMapping
    public SummonerDataResponse basicSummonerInfo(@RequestHeader(value = "Authorization") String bearer) {

        String oldToken = tokenSplit.splitToken(bearer);

        String name = jwtParser.getName(oldToken);

        log.info("name recived from token");
        String region = jwtParser.getRegion(oldToken);
        log.info("region recived from token");
        return riotRestClient.getSummonerData(name, region);
}
}