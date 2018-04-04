package pl.noip.lolstats.lol.stats.jwt;

public interface JwtChecker {
    void checkToken(String token);
}