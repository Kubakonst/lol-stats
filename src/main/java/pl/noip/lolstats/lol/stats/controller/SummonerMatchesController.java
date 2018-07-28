package pl.noip.lolstats.lol.stats.controller;

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
                log.info(participantIdentities.toString());
                for (player player : participantIdentities.getPlayer()) {
                    log.info(player.toString());
                    if (accountId == player.getAccountId()) {
                        log.info(accountId);
                        String participantsId = participantIdentities.getParticipantId();
                        log.info(participantsId);
                        for (participants participants : participantIDResponse.getParticipants()) {
                            log.info(participants.toString());
                            if (participantsId == participants.getParticipantId()) {
                                log.info(participantsId);
                                for (ParticipantStatsDto participantStatsDto : participants.getStats()) {
                                    singleMatch.setWin(participantStatsDto.getWin());
                                    log.info(participantStatsDto.toString());
                                    int kd = participantStatsDto.getKills() + participantStatsDto.getAssists();
                                    int kda = kd/participantStatsDto.getDeaths();
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