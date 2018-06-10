package pl.noip.lolstats.lol.stats.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Ignore
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class RiotRestClientTest extends Specification {

    @Autowired
    RiotRestClient riotRestClient

    @Ignore
    def "CheckUserNameInRiotDataBase"() {
        given:
        def name = "Piekaa"
        when:
        def list = riotRestClient.findSummonersRegions(name)
        then:
        list[0] == "eun1"
    }
}
// requires API key which lasts for one day