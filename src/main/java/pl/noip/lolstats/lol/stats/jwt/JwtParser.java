package pl.noip.lolstats.lol.stats.jwt;

import io.jsonwebtoken.Claims;

public interface JwtParser {

    Claims getData(String token);

    String getMail(Claims getData);

    String getName(Claims getData);
}