package pl.noip.lolstats.lol.stats.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.noip.lolstats.lol.stats.dto.SummonerDataResponse;
import pl.noip.lolstats.lol.stats.dto.SummonerMatchesResponde;

@Component
@Slf4j
public class RiotMatchesClient {
    @Value("${riot.api.key}")
    private String key;

    private RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders httpHeaders = new HttpHeaders();

    private HttpEntity httpEntity = new HttpEntity(httpHeaders);

    public SummonerMatchesResponde getMatchesData(String region, String id) {

        String url = "https://" + region + ".api.riotgames.com/lol/match/v3/matchlists/by-account/" + id + "?endIndex=3&api_key=" + key;
        log.info(url);
        ResponseEntity<SummonerMatchesResponde> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SummonerMatchesResponde.class);
        SummonerMatchesResponde summonerMatchesResponde = response.getBody();
        log.info("summoner matches get downloaded");
        return summonerMatchesResponde;
    }
}