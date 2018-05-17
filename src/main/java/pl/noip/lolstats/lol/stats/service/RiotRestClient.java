package pl.noip.lolstats.lol.stats.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class RiotRestClient {

//    @ControllerAdvice
//    public class ExceptionHandlerController {
//
//        @ExceptionHandler(NoHandlerFoundException.class)
//        @ResponseStatus(value = HttpStatus.NOT_FOUND)
//        @ResponseBody
//        public ErrorResponse requestHandlingNoHandlerFound() {
//            return new ErrorResponse("custom_404");
//        }
//    }

    @Value("${variable.secret-var}")
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

    public List<String> CheckUserNameInRiotDataBase(String name) {

        List<String> regions = new ArrayList<>();

        for (String reg : splitedRegions) {

            System.out.println(reg + " ");

            String url = "https://" + reg + ".api.riotgames.com/lol/summoner/v3/summoners/by-name/" + name + "?api_key=" + key;
        try {

            ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SummonerNameRequest.class);

            if (response.getStatusCode().value() == 200) {
                regions.add(reg);
            }
        }
            catch(Exception ex){

                }

        }
        return regions;
    }

}