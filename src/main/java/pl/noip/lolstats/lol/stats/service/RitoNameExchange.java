package pl.noip.lolstats.lol.stats.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;
import static org.junit.Assert.*;

public class RitoNameExchange {

    private SummonerNameRequest summonerNameRequest;

    private RestTemplate restTemplate = new RestTemplate();
//    private final String url = "https://eun1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + summonerNameRequest.getSumName() + "?api_key=" + key;
//    private HttpEntity<SummonerNameRequest> request = new HttpEntity<>(new SummonerNameRequest(sumName));
//    private ResponseEntity<SummonerNameRequest> response = restTemplate
//            .exchange(url, HttpMethod.POST, request, SummonerNameRequest.class);

//    assertThat(response.getStatusCode(), is(HttpStatus.OK));

//    private SummonerNameRequest sumNameRequest = response.getBody();
//
//    assertThat(sumNameRequest, notNullValue());
//    assertThat(sumNameRequest.getSumName(), is("bar"));


}