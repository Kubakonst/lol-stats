package pl.noip.lolstats.lol.stats.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class JwtInfo {

    private String email;
    private String name;
    private String region;
    private String token;
}
