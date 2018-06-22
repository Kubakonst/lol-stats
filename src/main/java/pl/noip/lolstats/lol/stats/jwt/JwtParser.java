package pl.noip.lolstats.lol.stats.jwt;

public interface JwtParser {

    String getMail(String token);

    String getName(String token);

    String getRegion(String token);
}