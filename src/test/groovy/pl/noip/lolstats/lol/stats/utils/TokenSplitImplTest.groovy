package pl.noip.lolstats.lol.stats.utils

import spock.lang.Specification

class TokenSplitImplTest extends Specification {

    TokenSplitImpl tokenSplit = new TokenSplitImpl()

    def "SplitToken"() {
        when:
        tokenSplit.splitToken(bearer)
        then:
        thrown(expectedException)
        where:
        bearer                 || expectedException
        "beare opdkasopdkasop" || NoBearerException
        "bearer "              || NoBearerException
    }
}
