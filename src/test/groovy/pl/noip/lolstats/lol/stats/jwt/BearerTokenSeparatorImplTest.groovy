package pl.noip.lolstats.lol.stats.jwt

import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException
import spock.lang.Specification
import spock.lang.Unroll

class BearerTokenSeparatorImplTest extends Specification {

    BearerTokenSeparatorImpl tokenSplit = new BearerTokenSeparatorImpl()

    @Unroll
    def "SplitToken"() {
        when:
        tokenSplit.getToken(bearer)
        then:
        thrown(expectedException)
        where:
        bearer                 || expectedException
        "beare opdkasopdkasop" || BearerNotPresentException
        "bearer "              || BearerNotPresentException
        "  "                   || BearerNotPresentException
        "gahkfhasu bearer"     || BearerNotPresentException

    }

    def "token is valid"() {
        expect:
        tokenSplit.getToken("bearer token") == "token"
    }
}