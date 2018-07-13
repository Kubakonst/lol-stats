package pl.noip.lolstats.lol.stats.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.noip.lolstats.lol.stats.Exceptions.RegionSearchException;
import pl.noip.lolstats.lol.stats.dto.*;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class RiotRestClient {
    @Value("${riot.api.key}")
    private String key;

    @Value("${regions}")
    private String propertiesRegions;


    private RestTemplate restTemplate = new RestTemplate();
    private AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();

    private HttpHeaders createHeaders(String apikey) {
        return new HttpHeaders() {{
            add("X-Riot-Token", apikey);
        }};
    }

//    private HttpHeaders httpHeaders = new HttpHeaders();
//
//    httpHeaders.add("X-Riot-Token", key);
//

//    private HttpEntity httpEntity = new HttpEntity(createHeaders(key));


    private String[] splitedRegions;

    @PostConstruct
    private void splitRegions() {

        splitedRegions = propertiesRegions.split(";");

    }

    public List<String> findSummonersRegions(String name) throws ExecutionException, InterruptedException {

        List<String> regions = new ArrayList<>();

        HashMap<ListenableFuture<ResponseEntity<SummonerNameRequest>>, String> listenableFutures = new HashMap<>();

        for (String reg : splitedRegions) {

            String url = "https://" + reg + ".api.riotgames.com/lol/summoner/v3/summoners/by-name/" + name;

            ListenableFuture<ResponseEntity<SummonerNameRequest>> listenableFuture = asyncRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity(createHeaders(key)), SummonerNameRequest.class);
            listenableFutures.put(listenableFuture, reg);
        }

        listenableFutures.forEach((key,value) ->
        {
                try {
                    if (key.get().getStatusCodeValue() == 200) {
                        regions.add(value);
                        log.info("there is a user in " + value);
                    }

                }
                catch (ExecutionException e) {
                    if (e.getCause() instanceof HttpClientErrorException) {
                        HttpClientErrorException ex = (HttpClientErrorException) e.getCause();
                        System.out.println(ex.getStatusCode());
                    }
                }

                catch (InterruptedException e){
                    log.error("RIOT server is not working");
                    throw new RegionSearchException();

            }
        });

        return regions;
    }

    public SummonerDataResponse getSummonerData(String name, String region) {

        String url = "https://" + region + ".api.riotgames.com/lol/summoner/v3/summoners/by-name/" + name;

        ResponseEntity<SummonerDataResponse> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(createHeaders(key)), SummonerDataResponse.class);
        SummonerDataResponse summonerDataResponse = response.getBody();
        log.info("summoner basic data get downloaded");
        return summonerDataResponse;
    }

    public MatchesResponse getMatchesData(String region, String id) {

        String url = "https://" + region + ".api.riotgames.com/lol/match/v3/matchlists/by-account/" + id + "?endIndex=3";
        ResponseEntity<MatchesResponse> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(createHeaders(key)), MatchesResponse.class);
        MatchesResponse matchesResponse = response.getBody();
        log.info("summoner matches get downloaded");

        return matchesResponse;
    }

    public ChampionNameResponse getChampionNameData(String id, String region) {
        String url = "https://" + region + ".api.riotgames.com/lol/static-data/v3/champions/" + id;
        ResponseEntity<ChampionNameResponse> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(createHeaders(key)), ChampionNameResponse.class);
        ChampionNameResponse championNameResponse = response.getBody();
        log.info("champion name get downloaded");

        return championNameResponse;
    }

    public SingleMatchData getSingleMatchData(String id, String region) {
        String url = "https://" + region + ".api.riotgames.com/lol/match/v3/matches/" + id;
        ResponseEntity<SingleMatchData> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(createHeaders(key)), SingleMatchData.class);
        SingleMatchData singleMatchData = response.getBody();
        log.info("game duration get downloaded");

        return singleMatchData;
    }

}