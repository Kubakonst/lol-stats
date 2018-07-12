//package pl.noip.lolstats.lol.stats.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.AsyncRestTemplate;
//import org.springframework.web.client.HttpClientErrorException;
//import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;
//
//import java.util.concurrent.ExecutionException;
//
//@RestController
//@RequestMapping("test")
//public class TestController {
//
//    @Value("${riot.api.key}")
//    private String key;
//
//    public SummonerNameRequest test() throws ExecutionException, InterruptedException {
//
//        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
//
//        String url = "https://eun1.api.riotgames.com/lol/summoner/v3/summoners/by-name/Piekaa";
//        String url2 = "https://kr.api.riotgames.com/lol/summoner/v3/summoners/by-name/Piekaa";
//
//        ListenableFuture<ResponseEntity<Object>> listenableFuture = asyncRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity(createHeaders(key)), Object.class);
//        listenableFuture.addCallback(
//                (result -> System.out.println(result)),
//                (throwable) -> {}// System.out.println(throwable)
//        );
//
//        ListenableFuture<ResponseEntity<Object>> listenableFuture2 = asyncRestTemplate.exchange(url2, HttpMethod.GET, new HttpEntity(createHeaders(key)), Object.class);
//        listenableFuture2.addCallback(
//                (result -> System.out.println(result)),
//                (throwable) ->  System.out.println("Wyjątek: " + throwable)
//
//        );
//
//        listenableFuture.get();
//
//        try {
//            listenableFuture2.get();
//        } catch (ExecutionException e) {
//            if( e.getCause() instanceof HttpClientErrorException) {
//                HttpClientErrorException ex = (HttpClientErrorException) e.getCause();
//                System.out.println(ex.getStatusCode());
//            }
//        }
//
//    }
//
//    private HttpHeaders createHeaders(String apikey) {
//        return new HttpHeaders() {{
//            add("X-Riot-Token", apikey);
//        }};
//    }
//
//
//}
