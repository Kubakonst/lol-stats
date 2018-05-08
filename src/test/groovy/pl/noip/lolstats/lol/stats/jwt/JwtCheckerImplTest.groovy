package pl.noip.lolstats.lol.stats.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureException
import pl.noip.lolstats.lol.stats.time.TimeService
import spock.lang.Specification

class JwtCheckerImplTest extends Specification {


    JwtCheckerImpl jwtChecker

    JwtGeneratorImpl jwtGenerator

    TimeService timeService = Mock()

    def setup() {
        def secret = "aaa"
        jwtChecker = new JwtCheckerImpl(timeService)
        jwtGenerator = new JwtGeneratorImpl(timeService)
        jwtGenerator.setSecret(secret)
        jwtChecker.setSecret(secret)
    }

    def "check valid token"() {

        given: "jwt is generated as second 1 and checked at second 2"
        timeService.millisSinceEpoch >>> [1000, 2000]
        and: "new token"
        def mail = "example@mail.com"
        def token = jwtGenerator.generate(mail)
        when: "token is checked"
        jwtChecker.checkToken(token)
        then: "nothing happens, which means that token is valid"
    }

    def "check expired token"() {

        given: "jwt is generated as second 1 and checked one hour later"
        timeService.millisSinceEpoch >>> [1000, 1000 * 60 * 60 + 2000]
        and: "new token"
        def mail = "example@mail.com"
        def token = jwtGenerator.generate(mail)
        when: "token is checked"
        jwtChecker.checkToken(token)
        then: "token is expired"
        thrown(ExpiredJwtException)
    }


    def "check token with wrong format"() {

        given: "jwt in incorrect format"
        def jwt = "eyJhbGciOiJIUzI1NiJ9eyJpYXQiOjEsImV4cCI6MzYwMX0.Q5HBk79pi0YzazlbpBT0kUXw8vwXcs76FcAw52DKaFI"
        when: "check token"
        jwtChecker.checkToken(jwt)
        then: "token is in incorrect format"
        thrown(MalformedJwtException)
    }


    def "check token with incorrect signature"() {
        given: "jwt is generated as second 1 and checked at second 2"
        timeService.millisSinceEpoch >>> [1000, 2000]
        and: "new token"
        def mail = "example@mail.com"
        def token = jwtGenerator.generate(mail)
        and: "incorrect secret is used for jwtCehcker"
        jwtChecker.secret = "rubbish"
        when: "token is checked"
        jwtChecker.checkToken(token)
        then: "signature is invalid"
        thrown(SignatureException)
    }

    //eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjEsImV4cCI6MzYwMX0.Q5HBk79pi0YzazlbpBT0kUXw8vwXcs76FcAw52DKaFI

}
