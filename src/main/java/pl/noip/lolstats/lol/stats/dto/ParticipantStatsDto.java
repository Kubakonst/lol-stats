package pl.noip.lolstats.lol.stats.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantStatsDto {
    private Boolean win;
    private int kills;
    private int assists;
    private int deaths;
}
