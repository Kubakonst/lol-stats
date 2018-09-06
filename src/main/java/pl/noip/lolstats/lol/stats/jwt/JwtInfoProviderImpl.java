package pl.noip.lolstats.lol.stats.jwt;

import org.springframework.stereotype.Component;
import pl.noip.lolstats.lol.stats.model.JwtInfo;

@Component
public class JwtInfoProviderImpl implements JwtInfoProvider {

    private JwtParser jwtParser;
    private BearerTokenSeparator bearerTokenSeparator;

    public JwtInfoProviderImpl(JwtParser jwtParser, BearerTokenSeparator bearerTokenSeparator) {
        this.jwtParser = jwtParser;
        this.bearerTokenSeparator = bearerTokenSeparator;
    }

    @Override
    public JwtInfo fromBearerToken(String bearerToken) {
        return jwtParser.jwtInfo(bearerTokenSeparator.getToken(bearerToken));
    }
}
