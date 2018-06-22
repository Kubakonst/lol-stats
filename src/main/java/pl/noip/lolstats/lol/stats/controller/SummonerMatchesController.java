package pl.noip.lolstats.lol.stats.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;
import pl.noip.lolstats.lol.stats.dto.SummonerMatchesResponde;
import pl.noip.lolstats.lol.stats.jwt.JwtParser;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;
import pl.noip.lolstats.lol.stats.service.RiotDataClient;
import pl.noip.lolstats.lol.stats.service.RiotMatchesClient;

@RestController
@RequestMapping("/api/summoner/matches")
@Slf4j
public class SummonerMatchesController {

    private JwtParser jwtParser;

    private TokenSplit tokenSplit;

    private RiotDataClient riotDataClient;

    private RiotMatchesClient riotMatchesClient;

    public SummonerMatchesController(RiotDataClient riotDataClient, JwtParser jwtParser, TokenSplit tokenSplit, RiotMatchesClient riotMatchesClient) {
        this.riotDataClient = riotDataClient;
        this.jwtParser = jwtParser;
        this.tokenSplit = tokenSplit;
        this.riotMatchesClient = riotMatchesClient;
    }

    @PostMapping
    public SummonerMatchesResponde data(@RequestHeader(value = "Authorization") String bearer) {

        if (bearer == null) {
            throw new BearerNotPresentException();
        }

        String oldToken = tokenSplit.splitToken(bearer);

        String name = jwtParser.getName(oldToken);

        String region = jwtParser.getRegion(oldToken);

        String id = riotDataClient.getSummonerData(name, region).getAccountId();

        return riotMatchesClient.getMatchesData(region, id);
    }
}