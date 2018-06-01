package pl.noip.lolstats.lol.stats.jwt;

import pl.noip.lolstats.lol.stats.model.Account;

public interface JwtGenerator {

    String generate(Account account);
}
