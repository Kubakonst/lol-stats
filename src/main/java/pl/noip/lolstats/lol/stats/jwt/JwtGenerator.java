package pl.noip.lolstats.lol.stats.jwt;

public interface JwtGenerator {
    String generate(String email);

    String generate(String email, String sumName, String region);
}
