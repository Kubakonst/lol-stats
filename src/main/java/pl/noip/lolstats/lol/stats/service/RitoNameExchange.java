package pl.noip.lolstats.lol.stats.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;

public class RitoNameExchange {

    private SummonerNameRequest summonerNameRequest;

    private String key;

    private final String url = "https://eun1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + summonerNameRequest.getSumName() + "?api_key=" + key;

    private RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders httpHeaders = new HttpHeaders();

    private HttpEntity httpEntity  = new HttpEntity(httpHeaders);
    ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, SummonerNameRequest.class);

}