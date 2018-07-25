package pl.noip.lolstats.lol.stats.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class RiotDataClientTest extends Specification {

    @Autowired
    RiotRestClient riotRestClient;

    def "CheckUserNameInRiotDataBase"() {
        given:
        def name = "Piekaa"
        def region = "eun1"
        when:
        def summonerDataResponse = riotRestClient.getSummonerData(name, region)
        then:
        def summonerID = summonerDataResponse.getId()
        def accountID = summonerDataResponse.getAccountId()
        summonerID == "47104921"
        accountID == "210298793"
    }

// requires API key which lasts for one day

}