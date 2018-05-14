package pl.noip.lolstats.lol.stats.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;

import java.util.ArrayList;
import java.util.List;


public class RiotRestClient {

    @Value("${variable.secret-var}")
    private String key;
    public void setKey(String key) {
        this.key = key;
    }

    private RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders httpHeaders = new HttpHeaders();

    private HttpEntity httpEntity = new HttpEntity(httpHeaders);

    public List<String> CheckUserNameInRiotDataBase(String name) {

        String url = "https://eun1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + name + "?api_key=" + key;

        List<String> regions = new ArrayList<>();

        ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SummonerNameRequest.class);

        if (response.getStatusCode().value() == 200) {

            regions.add("eun1");

        }

        return regions;
    }
}