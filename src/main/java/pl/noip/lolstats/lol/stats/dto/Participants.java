package pl.noip.lolstats.lol.stats.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Participants {
    private ParticipantStatsDto stats;
    private String participantId;
}
