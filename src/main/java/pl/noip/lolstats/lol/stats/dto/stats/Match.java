package pl.noip.lolstats.lol.stats.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
    private boolean win;
    private String participantId;
    private double kda;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
}
