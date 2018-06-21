package pl.noip.lolstats.lol.stats.jwt

import org.springframework.beans.factory.annotation.Autowired
import pl.noip.lolstats.lol.stats.model.Account
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

    def "Get user mail from token"() {
        given: "time for not expired token"
        timeService.millisSinceEpoch >> 1000 * 1524664497
        expect:
        jwtParser.getMail("eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImV4YW1wbGVAbWFpbC5jb20iLCJpYXQiOjE1MjQ2NjA4OTcsImV4cCI6MTUyNDY2NDQ5N30.1yjYT7K-7r8eN8-p9PwSjbs8Tn_nP_rpIuhAHJ2QBlM") == "example@mail.com"

    }

    def "Get user name from token"() {
        given: "time for not expired token"
        timeService.millisSinceEpoch >> 1000 * 1524664497
        expect:
        jwtParser.getName("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiRXhhbXBsZU5hbWUiLCJlbWFpbCI6ImV4YW1wbGVAbWFpbC5jb20iLCJpYXQiOjE1MjUyNjc2ODcsImV4cCI6MTUyNTI3MTI4N30.KKo56lI6e5UaeSBpDdBk-yCkWjsvNTssXQUUm3uZ0PI") == "ExampleName"

    }

    def "Genereta correct token with user name"() {
        given: "jwt is generated as second 1 and checked at second 2"
        timeService.millisSinceEpoch >>> [1000, 2000]
        expect:
        jwtParser.getMail(jwtGenerator.generate(account)) == "example@mail.com"

    }
}