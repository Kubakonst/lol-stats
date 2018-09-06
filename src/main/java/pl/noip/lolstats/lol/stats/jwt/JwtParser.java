package pl.noip.lolstats.lol.stats.jwt;

import pl.noip.lolstats.lol.stats.model.JwtInfo;

public interface JwtParser {

    JwtInfo jwtInfo(String token);
}