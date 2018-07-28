package pl.noip.lolstats.lol.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    private String lane;
    private String gameId;
    private String champion;
    private String platformId;
    private String timestamp;
    private String queue;
    private String role;
    private String season;
    private String championName;
    private String gameDuration;
    private Boolean win;
    private String participantId;
    private int kda;
}
