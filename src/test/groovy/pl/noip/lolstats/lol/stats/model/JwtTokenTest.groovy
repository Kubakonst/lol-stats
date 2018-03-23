package pl.noip.lolstats.lol.stats.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.noip.lolstats.lol.stats.jwt.JwtGenerator
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("test")
class JwtTokenTest extends Specification {

    @Autowired
    JwtGenerator jwtGenerator

    def "test"() {
        given:

        String e = jwtGenerator.generate()
        println(e)

    }

}
