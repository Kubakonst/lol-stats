package pl.noip.lolstats.lol.stats.rest

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import spock.lang.Specification


@SpringBootTest
class RestTest extends Specification {

    def "RestTemplate test"() {


        given:
        def restTemplate = new RestTemplate()
        and:
        def httpHeaders = new HttpHeaders()
        def httpEntity = new HttpEntity(httpHeaders)
        when:
        def response = restTemplate.exchange("http://dev-lol-stats.noip.pl/lol", HttpMethod.GET, httpEntity, LolResponse.class)
        then:
        response.statusCode.value() == 200
        response.body.number == 123456
    }

}

class LolResponse {
    private int number

    int getNumber() {
        return number
    }

    void setNumber(int number) {
        this.number = number
    }
}
