package pl.noip.lolstats.lol.stats.jwt

import pl.noip.lolstats.lol.stats.time.TimeService
import spock.lang.Specification

class JwtParserImplTest extends Specification {

    JwtParserImpl jwtParser

    TimeService timeService = Mock()

    def setup(){
        jwtParser = new JwtParserImpl(timeService)
        jwtParser.setSecret("dh1asg2fhksdf4jkla9edhgfk8jadsh7flas3dsdf4gbhjkfews5rrtweherhedrtf6gwetygedrgwergsed2rgwergwrfgwefwe")
    }

    def "MailGet"(){
        given: "time for not expired token"
        timeService.millisSinceEpoch >> 1000 * 1524664497
                expect:
                jwtParser.getmail("eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImV4YW1wbGVAbWFpbC5jb20iLCJpYXQiOjE1MjQ2NjA4OTcsImV4cCI6MTUyNDY2NDQ5N30.1yjYT7K-7r8eN8-p9PwSjbs8Tn_nP_rpIuhAHJ2QBlM") == "example@mail.com"

        }
}