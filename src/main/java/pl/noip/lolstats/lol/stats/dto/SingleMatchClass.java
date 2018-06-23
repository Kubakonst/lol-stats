package pl.noip.lolstats.lol.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SingleMatchClass {
    private String lane;
    private String gameId;
    private String champion;
    private String platformId;
    private String timestamp;
    private String queue;
    private String role;
    private String season;
}
