package pl.noip.lolstats.lol.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;
import pl.noip.lolstats.lol.stats.dto.Match;
import pl.noip.lolstats.lol.stats.dto.SummonerMatchesResponse;
import pl.noip.lolstats.lol.stats.jwt.JwtParser;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;
import pl.noip.lolstats.lol.stats.service.RiotRestClient;

import java.util.List;

@RestController
@RequestMapping("/api/summoner/matches")
@Slf4j
public class SummonerMatchesController {

    private JwtParser jwtParser;

    private TokenSplit tokenSplit;

    private RiotRestClient riotRestClient;

    public SummonerMatchesController(RiotRestClient riotRestClient, JwtParser jwtParser, TokenSplit tokenSplit) {
        this.riotRestClient = riotRestClient;
        this.jwtParser = jwtParser;
        this.tokenSplit = tokenSplit;
    }

    @PostMapping
    public SummonerMatchesResponse data(@RequestHeader(value = "Authorization") String bearer) {

        if (bearer == null) {
            throw new BearerNotPresentException();
        }

        String oldToken = tokenSplit.splitToken(bearer);

        String name = jwtParser.getName(oldToken);

        String region = jwtParser.getRegion(oldToken);

        String id = riotRestClient.getSummonerData(name, region).getAccountId();

        SummonerMatchesResponse summonerMatches = riotRestClient.getMatchesData(region, id);

        for( Match singleMatch : summonerMatches.getMatches()){
                singleMatch.setChampionName(riotRestClient.getChampionNameData(singleMatch.getChampion(), region).getName());
                singleMatch.setGameDuration(riotRestClient.getSingleMatchData(singleMatch.getGameId(), region).getGameDuration());
        }

        return summonerMatches;
    }
}