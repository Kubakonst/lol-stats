package pl.noip.lolstats.lol.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.dto.*;
import pl.noip.lolstats.lol.stats.jwt.JwtParser;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;
import pl.noip.lolstats.lol.stats.service.RiotRestClient;

@RestController
@RequestMapping("/api/matches/matchId")
@Slf4j
public class MatchDataController {

    private JwtParser jwtParser;

    private TokenSplit tokenSplit;

    private RiotRestClient riotRestClient;

    public MatchDataController(RiotRestClient riotRestClient, JwtParser jwtParser, TokenSplit tokenSplit) {
        this.riotRestClient = riotRestClient;
        this.jwtParser = jwtParser;
        this.tokenSplit = tokenSplit;
    }

    @PostMapping
    public MatchesResponse data(@RequestHeader(value = "Authorization") String bearer) {

        String oldToken = tokenSplit.splitToken(bearer);

        String name = jwtParser.getName(oldToken);

        String region = jwtParser.getRegion(oldToken);

        String accountId = riotRestClient.getSummonerData(name, region).getAccountId();

        MatchesResponse summonerMatches = riotRestClient.getMatchesData(region, accountId);

        for (Match singleMatch : summonerMatches.getMatches()) {
            ParticipantIDResponse participantIDResponse = riotRestClient.getPlayerpartID(singleMatch.getGameId(), region);
            for (SingleParticipantIDResponse singleParticipantIDResponse : riotRestClient.getPlayerpartID(singleMatch.getGameId(), region).getParticipantIdentities()) {
                for (SinglePlayerInfoResponse singlePlayerInfoResponse : singleParticipantIDResponse.getPlayer()) {
                    if (accountId == singlePlayerInfoResponse.getAccountId()) {
                        String participantsId = singleParticipantIDResponse.getParticipantId();
                        for (SinglePlayerStatsResponse singlePlayerStatsResponse : participantIDResponse.getParticipants()) {
                            if (participantsId == singlePlayerStatsResponse.getParticipantId()) {
                                for (SingleMatchPlayerData singleMatchPlayerData = singlePlayerStatsResponse.getStats()) {
                                    singleMatch.setWin(singleMatchPlayerData.getWin());
                                    int kd = singleMatchPlayerData.getKills() + singleMatchPlayerData.getAssists();
                                    int kda = kd/singleMatchPlayerData.getDeaths();
                                    singleMatch.setKda(kda);
                                }
                            }
                        }
                    }
                }
            }
        }
        return summonerMatches;
    }
}