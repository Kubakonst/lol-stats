package pl.noip.lolstats.lol.stats.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MatchBasicInfo {
    private String lane;
    private String gameId;
    private String champion;
    private String platformId;
    private String timestamp;
    private String queue;
    private String role;
    private String season;

}
