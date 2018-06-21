package pl.noip.lolstats.lol.stats.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.noip.lolstats.lol.stats.dto.SummonerDataResponse;

@Component
@Slf4j
public class RiotDataClient {
    @Value("${riot.api.key}")
    private String key;

    private RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders httpHeaders = new HttpHeaders();

    private HttpEntity httpEntity = new HttpEntity(httpHeaders);

    public SummonerDataResponse getSummonerData(String name, String region) {

            String url = "https://" + region + ".api.riotgames.com/lol/summoner/v3/summoners/by-name/" + name + "?api_key=" + key;

                ResponseEntity<SummonerDataResponse> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SummonerDataResponse.class);
                SummonerDataResponse summonerDataResponse = response.getBody();
                log.info("summoner basic data get downloaded");
                return summonerDataResponse;
    }
}