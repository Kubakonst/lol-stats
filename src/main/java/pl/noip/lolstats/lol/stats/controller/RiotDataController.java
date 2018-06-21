package pl.noip.lolstats.lol.stats.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.dto.SummonerDataResponse;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;
import pl.noip.lolstats.lol.stats.service.RiotDataClient;


@RestController
@RequestMapping("/api/summoner/riotData")
@Slf4j
public class RiotDataController {

    private RiotDataClient riotDataClient;

    public RiotDataController(RiotDataClient riotDataClient) {
        this.riotDataClient = riotDataClient;
    }

    @PostMapping
    public ResponseEntity<?> name(@RequestBody SummonerNameRequest summonerNameRequest) {

        riotDataClient.getSummonerData(summonerNameRequest.getSumName(),summonerNameRequest.getRegion());

        return new ResponseEntity<>(new SummonerDataResponse(), HttpStatus.OK);
    }

}