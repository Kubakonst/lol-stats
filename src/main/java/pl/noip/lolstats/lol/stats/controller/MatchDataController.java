//package pl.noip.lolstats.lol.stats.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import pl.noip.lolstats.lol.stats.dto.*;
//import pl.noip.lolstats.lol.stats.jwt.JwtParser;
//import pl.noip.lolstats.lol.stats.jwt.TokenSplit;
//import pl.noip.lolstats.lol.stats.service.RiotRestClient;
//
//@RestController
//@RequestMapping("/api/matches/matchId")
//@Slf4j
//public class MatchDataController {
//
//    private JwtParser jwtParser;
//
//    private TokenSplit tokenSplit;
//
//    private RiotRestClient riotRestClient;
//
//    public MatchDataController(RiotRestClient riotRestClient, JwtParser jwtParser, TokenSplit tokenSplit) {
//        this.riotRestClient = riotRestClient;
//        this.jwtParser = jwtParser;
//        this.tokenSplit = tokenSplit;
//    }
//
//    @PostMapping
//    public MatchesResponse data(@RequestHeader(value = "Authorization") String bearer) {
//
//        String oldToken = tokenSplit.splitToken(bearer);
//
//        String name = jwtParser.getName(oldToken);
//
//        String region = jwtParser.getRegion(oldToken);
//
//        String accountId = riotRestClient.getSummonerData(name, region).getAccountId();
//
//        MatchesResponse summonerMatches = riotRestClient.getMatchesData(region, accountId);
//
//        for (Match singleMatch : summonerMatches.getMatches()) {
//            ParticipantIDResponse participantIDResponse = riotRestClient.getPlayerpartID(singleMatch.getGameId(), region);
//            for (participantIdentities participantIdentities : riotRestClient.getPlayerpartID(singleMatch.getGameId(), region).getFullDataMatchResponse()) {
//                for (player player : participantIdentities.getPlayer()) {
//                    if (accountId == player.getAccountId()) {
//                        String participantsId = participantIdentities.getParticipantId();
//                        for (stats stats : participantIDResponse.getParticipants()) {
//                            if (participantsId == stats.getParticipantId()) {
//                                for (SingleMatchPlayerData singleMatchPlayerData : stats.getStats()) {
//                                    singleMatch.setWin(singleMatchPlayerData.getWin());
//                                    log.info(singleMatchPlayerData.getWin().toString());
//                                    int kd = singleMatchPlayerData.getKills() + singleMatchPlayerData.getAssists();
//                                    int kda = kd/singleMatchPlayerData.getDeaths();
//                                    singleMatch.setKda(kda);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return summonerMatches;
//    }
//}