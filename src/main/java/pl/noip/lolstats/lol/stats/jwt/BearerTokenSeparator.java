package pl.noip.lolstats.lol.stats.jwt;

public interface BearerTokenSeparator {

    String getToken(String bearerToken);
}
