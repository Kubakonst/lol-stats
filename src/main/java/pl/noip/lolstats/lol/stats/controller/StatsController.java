package pl.noip.lolstats.lol.stats.controller;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.noip.lolstats.lol.stats.dto.stats.*;
import pl.noip.lolstats.lol.stats.jwt.JwtInfoProvider;
import pl.noip.lolstats.lol.stats.model.JwtInfo;
import pl.noip.lolstats.lol.stats.service.ChampionService;
import pl.noip.lolstats.lol.stats.service.RiotRestClient;

import java.util.List;

@ToString
@RestController
@RequestMapping("/api/stats")
@Slf4j
public class StatsController {

    private JwtInfoProvider jwtInfoProvider;
    private RiotRestClient riotRestClient;
    private ChampionService championService;

    public StatsController(JwtInfoProvider jwtInfoProvider, RiotRestClient riotRestClient, ChampionService championService) {
        this.jwtInfoProvider = jwtInfoProvider;
        this.riotRestClient = riotRestClient;
        this.championService = championService;
    }

    @GetMapping("/summoner/league")
    public List<SummonerLeagueResponse> league(@RequestHeader(value = "Authorization") String bearer) {
        JwtInfo jwtInfo = jwtInfoProvider.fromBearerToken(bearer);
        String id = riotRestClient.getSummonerData(jwtInfo.getName(), jwtInfo.getRegion()).getId();
        return riotRestClient.getSummonerLeague(id, jwtInfo.getRegion());
    }

    @GetMapping("/summoner/basicInfo")
    public SummonerBasicInfoResponse basicSummonerInfo(@RequestHeader(value = "Authorization") String bearer) {
        JwtInfo jwtInfo = jwtInfoProvider.fromBearerToken(bearer);
        return riotRestClient.getSummonerData(jwtInfo.getName(), jwtInfo.getRegion());
    }

    @GetMapping("/summoner/matches")
    public MatchesResponse matches(@RequestHeader(value = "Authorization") String bearer) {

        JwtInfo jwtInfo = jwtInfoProvider.fromBearerToken(bearer);
        String accountId = riotRestClient.getSummonerData(jwtInfo.getName(), jwtInfo.getRegion()).getAccountId();

        MatchesResponse matchesResponse = riotRestClient.getMatchesData(jwtInfo.getRegion(), accountId);

        for (Match singleMatch : matchesResponse.getMatches()) {
            singleMatch.setChampionName(championService.getChampionName(singleMatch.getChampion()));
            singleMatch.setGameDuration(riotRestClient.getSingleMatchData(singleMatch.getGameId(), jwtInfo.getRegion()).getGameDuration());
        }

        for (Match singleMatch : matchesResponse.getMatches()) {
            MatchResponse matchResponse = riotRestClient.getMatchData(singleMatch.getGameId(), jwtInfo.getRegion());
            String participantId = matchResponse.getParticipantIdentities().stream()
                    .filter(e -> e.getPlayer().getAccountId().equals(accountId)).findFirst().get().getParticipantId();
            log.debug(participantId);
            ParticipantStats stats = matchResponse.getParticipants().stream()
                    .filter(e -> e.getParticipantId().equals(participantId)).findFirst().get().getStats();
            singleMatch.setWin(stats.isWin());
            double ka = stats.getKills() + stats.getAssists();
            double deaths = stats.getDeaths();
            if (deaths == 0) {
                deaths = 1;
            }
            double kda = ka / deaths;
            singleMatch.setKda(kda);
            singleMatch.setItem0(stats.getItem0());
            singleMatch.setItem1(stats.getItem1());
            singleMatch.setItem2(stats.getItem2());
            singleMatch.setItem3(stats.getItem3());
            singleMatch.setItem4(stats.getItem4());
            singleMatch.setItem5(stats.getItem5());
        }
        return matchesResponse;
    }

    @GetMapping("/summoner/regions")
    public ResponseEntity<?> name(@RequestParam String name) {
        List<String> userRegions = riotRestClient.findSummonersRegions(name);
        log.info("There are " + userRegions.size() + " regions for summoner: " + userRegions);
        return new ResponseEntity<>(new RegionsResponse(userRegions), HttpStatus.OK);
    }

}