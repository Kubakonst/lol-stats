package pl.noip.lolstats.lol.stats.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noip.lolstats.lol.stats.dto.RiotNameResponse;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;
import pl.noip.lolstats.lol.stats.service.RiotRestClient;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/summoner/riotName")
@Slf4j
public class RiotNameController {

    private RiotRestClient riotRestClient;

    public RiotNameController(RiotRestClient riotRestClient) {
        this.riotRestClient = riotRestClient;
    }

    @PostMapping
    public ResponseEntity<?> name(@RequestBody SummonerNameRequest summonerNameRequest) throws ExecutionException, InterruptedException{

        List<String> userRegions = riotRestClient.findSummonersRegions(summonerNameRequest.getSumName());
        log.info("there are " + userRegions.size() + " regions for that summoner name");
        return new ResponseEntity<>(new RiotNameResponse(userRegions), HttpStatus.OK);
    }

}