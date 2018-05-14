package pl.noip.lolstats.lol.stats.service

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import pl.noip.lolstats.lol.stats.dto.SummonerNameRequest
import spock.lang.Specification

@SpringBootTest
class RitoNameExchangeTest extends Specification {


    def "RestTemplate test"() {

        given:
        def url = "https://eun1.api.riotgames.com/lol/summoner/v3/summoners/by-name/Piekaa?api_key=RGAPI-5b47439c-031b-4730-897a-3762072e2748"
        def restTemplate = new RestTemplate()
        and:
        def httpHeaders = new HttpHeaders()
        def httpEntity = new HttpEntity(httpHeaders)
        when:
        def response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, SummonerNameRequest.class)
        then:
        response.statusCode.value() == 200
//        response.body.name == "Piekaa"
    }

}