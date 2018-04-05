package pl.noip.lolstats.lol.stats.utils

import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException
import pl.noip.lolstats.lol.stats.jwt.TokenSplitImpl
import spock.lang.Specification
import spock.lang.Unroll

class TokenSplitImplTest extends Specification {

    TokenSplitImpl tokenSplit = new TokenSplitImpl()

    @Unroll
    def "SplitToken"() {
        when:
        tokenSplit.splitToken(bearer)
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
    tokenSplit.splitToken("bearer token")=="token"
}
}