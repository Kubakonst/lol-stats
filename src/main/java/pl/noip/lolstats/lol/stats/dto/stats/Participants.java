package pl.noip.lolstats.lol.stats.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Participants {
    private ParticipantStats stats;
    private String participantId;
}
