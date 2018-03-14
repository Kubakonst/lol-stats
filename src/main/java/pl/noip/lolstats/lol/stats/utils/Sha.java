package pl.noip.lolstats.lol.stats.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Sha {
    public static String hash(String text) {
        return Hashing.sha256()
                .hashString(text, StandardCharsets.UTF_8)
                .toString();
    }
}
