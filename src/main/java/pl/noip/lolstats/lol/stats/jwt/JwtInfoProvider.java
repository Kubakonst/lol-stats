package pl.noip.lolstats.lol.stats.jwt;

import pl.noip.lolstats.lol.stats.model.JwtInfo;

public interface JwtInfoProvider {

    JwtInfo fromBearerToken(String bearerToken);
}
