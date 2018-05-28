package pl.noip.lolstats.lol.stats.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.noip.lolstats.lol.stats.Exceptions.HttpClientErrorException;
import pl.noip.lolstats.lol.stats.Exceptions.InternetServerErrorException;
import pl.noip.lolstats.lol.stats.Exceptions.ServiceUnavailableErrorException;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class RiotRestClient {

    @Value("${riot.api.key}")
    private String key;

    @Value("${regions}")
    private String propertiesRegions;

    private RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders httpHeaders = new HttpHeaders();

    private HttpEntity httpEntity = new HttpEntity(httpHeaders);

    private String[] splitedRegions;

    @PostConstruct
    private void splitRegions() {

        splitedRegions = propertiesRegions.split(";");

    }

    public List<String> findSummonersRegions(String name) {

        List<String> regions = new ArrayList<>();

        for (String reg : splitedRegions) {

            String url = "https://" + reg + ".api.riotgames.com/lol/summoner/v3/summoners/by-name/" + name + "?api_key=" + key;
            try {

                ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SummonerNameRequest.class);

                if (response.getStatusCode().value() == 200) {
                    regions.add(reg);
                }

                if (response.getStatusCode().value() == 404) {
                    throw new HttpClientErrorException();
                }

                if (response.getStatusCode().value() == 500) {
                    throw new InternetServerErrorException();
                }

                if (response.getStatusCode().value() == 503) {
                    throw new ServiceUnavailableErrorException();
                }
            } catch (Exception ex) {

            }

        }
        return regions;
    }

}