package pl.noip.lolstats.lol.stats.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("test")
public class TestController {

    @Value("${riot.api.key}")
    private String key;

    public SummonerNameRequest test() throws ExecutionException, InterruptedException {

        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();

        String url = "https://eu.api.riotgames.com/lol/summoner/v3/summoners/by-name/Piekaa";

        ListenableFuture<ResponseEntity<SummonerNameRequest>> listenableFuture = asyncRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity(createHeaders(key)), SummonerNameRequest.class);
        listenableFuture.addCallback(
                (result -> System.out.println(result)),
                (throwable) -> System.out.println(throwable)
        );
        ResponseEntity<SummonerNameRequest> summonerNameRequestResponseEntity = listenableFuture.get();
        return summonerNameRequestResponseEntity.getBody();

    }

    private HttpHeaders createHeaders(String apikey) {
        return new HttpHeaders() {{
            add("X-Riot-Token", apikey);
        }};
    }


}
