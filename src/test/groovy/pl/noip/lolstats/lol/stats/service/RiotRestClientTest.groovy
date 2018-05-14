package pl.noip.lolstats.lol.stats.service

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


@SpringBootTest
class RiotRestClientTest extends Specification {

    RiotRestClient riotRestClient = new RiotRestClient();

    def setup(){
        riotRestClient.setKey("ENC(VMAckVUvHhbiEpU1pG4JuFyc3WWR5CJ8FkRTmpcZoow=)")
    }

    def "CheckUserNameInRiotDataBase"() {
        given:
        def name = "Piekaa"
        when:
        def list = riotRestClient.CheckUserNameInRiotDataBase(name)
        then:
        list[0] == "eun1"
    }
}