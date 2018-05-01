package pl.noip.lolstats.lol.stats.jwt

import pl.noip.lolstats.lol.stats.time.TimeService
import spock.lang.Specification

class JwtParserImplTest extends Specification {

    JwtParserImpl jwtParser

    JwtGeneratorImpl jwtGenerator

    TimeService timeService = Mock()

    def setup(){
        jwtParser = new JwtParserImpl(timeService)
        jwtParser.setSecret("dh1asg2fhksdf4jkla9edhgfk8jadsh7flas3dsdf4gbhjkfews5rrtweherhedrtf6gwetygedrgwergsed2rgwergwrfgwefwe")
        jwtGenerator = new JwtGeneratorImpl(timeService)
        jwtGenerator.setSecret("dh1asg2fhksdf4jkla9edhgfk8jadsh7flas3dsdf4gbhjkfews5rrtweherhedrtf6gwetygedrgwergsed2rgwergwrfgwefwe")
    }

    def "MailGet"(){
        given: "jwt is generated as second 1 and checked at second 2"
        timeService.millisSinceEpoch >>> [1000, 2000]
        def mail = "example@mail.com"
                expect:
                jwtParser.getMail(jwtGenerator.generate(mail)) == "example@mail.com"

        }
}