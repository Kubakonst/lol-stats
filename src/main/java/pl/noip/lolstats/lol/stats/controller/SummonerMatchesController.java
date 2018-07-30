package pl.noip.lolstats.lol.stats.controller;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.dto.*;
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
            ParticipantIDResponse participantIDResponse = riotRestClient.getPlayerpartID(singleMatch.getGameId(), region);
            log.info(participantIDResponse.toString());
            for (participantIdentities participantIdentities : participantIDResponse.getParticipantIdentities()) {
                if(participantIdentities.getPlayer().getAccountId().equals(accountId)){
                    String participantId = participantIdentities.getParticipantId();
                    for(participants participants : participantIDResponse.getParticipants()){
                        if(participantId.equals(participants.getParticipantId())){
                            singleMatch.setWin(participants.getStats().getWin());
                            double ka = participants.getStats().getKills() + participants.getStats().getAssists();
                            double deaths = participants.getStats().getDeaths();
                            if(deaths == 0){
                                deaths = 1;
                            }
                            double kda = ka/deaths;
                            singleMatch.setKda(kda);
                        }
                    }
                }
            }
       }

        return summonerMatches;
    }
}