package pl.noip.lolstats.lol.stats.controller;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.dto.Match;
import pl.noip.lolstats.lol.stats.dto.MatchesResponse;
import pl.noip.lolstats.lol.stats.dto.ParticipantStatsDto;
import pl.noip.lolstats.lol.stats.dto.SpecificMatchResponse;
import pl.noip.lolstats.lol.stats.jwt.JwtParser;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;
import pl.noip.lolstats.lol.stats.service.ChampionService;
import pl.noip.lolstats.lol.stats.service.RiotRestClient;

@ToString
@RestController
@RequestMapping("/api/summoner/matches")
@Slf4j
public class SummonerMatchesController {

    private JwtParser jwtParser;

    private TokenSplit tokenSplit;

    private RiotRestClient riotRestClient;

    private ChampionService championService;

    public SummonerMatchesController(RiotRestClient riotRestClient, JwtParser jwtParser, TokenSplit tokenSplit, ChampionService championService) {
        this.riotRestClient = riotRestClient;
        this.jwtParser = jwtParser;
        this.tokenSplit = tokenSplit;
        this.championService = championService;
    }

    @PostMapping
    public MatchesResponse data(@RequestHeader(value = "Authorization") String bearer) {

        String oldToken = tokenSplit.splitToken(bearer);

        String name = jwtParser.getName(oldToken);

        String region = jwtParser.getRegion(oldToken);

        String id = riotRestClient.getSummonerData(name, region).getAccountId();

        String accountId = riotRestClient.getSummonerData(name, region).getAccountId();

        MatchesResponse summonerMatches = riotRestClient.getMatchesData(region, id);

        for (Match singleMatch : summonerMatches.getMatches()) {
            singleMatch.setChampionName(championService.getChampionName(singleMatch.getChampion()));
            singleMatch.setGameDuration(riotRestClient.getSingleMatchData(singleMatch.getGameId(), region).getGameDuration());
        }

        for (Match singleMatch : summonerMatches.getMatches()) {
            SpecificMatchResponse specificMatchResponse = riotRestClient.getPlayerpartID(singleMatch.getGameId(), region);
            String participantId = specificMatchResponse.getParticipantIdentities().stream()
                    .filter(e -> e.getPlayer().getAccountId().equals(accountId)).findFirst().get().getParticipantId();
            log.debug(participantId);
            ParticipantStatsDto stats = specificMatchResponse.getParticipants().stream()
                    .filter(e -> e.getParticipantId().equals(participantId)).findFirst().get().getStats();
            singleMatch.setWin(stats.isWin());
            double ka = stats.getKills() + stats.getAssists();
            double deaths = stats.getDeaths();
            if (deaths == 0) {
                deaths = 1;
            }
            double kda = ka / deaths;
            singleMatch.setKda(kda);
        }
        return summonerMatches;
    }
}