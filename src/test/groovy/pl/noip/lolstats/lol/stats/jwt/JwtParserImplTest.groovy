package pl.noip.lolstats.lol.stats.jwt

import org.springframework.beans.factory.annotation.Autowired
import pl.noip.lolstats.lol.stats.model.Account
import pl.noip.lolstats.lol.stats.model.JwtInfo
import pl.noip.lolstats.lol.stats.repository.AccountRepository
import pl.noip.lolstats.lol.stats.time.TimeService
import pl.noip.lolstats.lol.stats.utils.Sha
import spock.lang.Specification

class JwtParserImplTest extends Specification {

    JwtParserImpl jwtParser

    JwtGeneratorImpl jwtGenerator

    TimeService timeService = Mock()

    def account = Account.builder()
            .email("example@mail.com")
            .passwordHash(Sha.hash("examplePassword"))
            .sumName("exampleName")
            .region("exampleRegion")
            .id("1354653")
            .accountId("123151")
            .build()

    @Autowired
    private AccountRepository repository

    def setup() {
        jwtParser = new JwtParserImpl(timeService)
        jwtParser.setSecret("dh1asg2fhksdf4jkla9edhgfk8jadsh7flas3dsdf4gbhjkfews5rrtweherhedrtf6gwetygedrgwergsed2rgwergwrfgwefwe")
        jwtGenerator = new JwtGeneratorImpl(timeService)
        jwtGenerator.setSecret("dh1asg2fhksdf4jkla9edhgfk8jadsh7flas3dsdf4gbhjkfews5rrtweherhedrtf6gwetygedrgwergsed2rgwergwrfgwefwe")


    }

    def "Genereta correct token with user name"() {
        given: "jwt is generated as second 1 and checked at second 2"
        timeService.millisSinceEpoch >>> [1000, 2000]
        def token = jwtGenerator.generate(account)
        expect:
        jwtParser.jwtInfo(token) == JwtInfo.builder().email("example@mail.com").name("exampleName").region("exampleRegion").token(token).build()

    }
}