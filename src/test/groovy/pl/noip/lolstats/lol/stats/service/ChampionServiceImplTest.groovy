package pl.noip.lolstats.lol.stats.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class ChampionServiceImplTest extends Specification {

    @Autowired
    ChampionService championService;

    def "GetChampionName"() {
        given:
        def id = "266"
        when:
        def name = championService.getChampionNameData(id);
        then:
        name == "Aatrox"
    }
}
