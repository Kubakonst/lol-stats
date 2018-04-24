package pl.noip.lolstats.lol.stats.jwt

import spock.lang.Specification
import spock.lang.Unroll

class JwtGetMailImplTest extends Specification {

    JwtGetMailImpl getMail = new JwtGetMailImpl()

        @Unroll
    def "MailGet"(){
            given:
            getMail.getmail(token);
            def token=

        }
}