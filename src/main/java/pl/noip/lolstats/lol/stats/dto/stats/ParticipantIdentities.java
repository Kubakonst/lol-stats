package pl.noip.lolstats.lol.stats.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantIdentities {
    private Player player;
    private String participantId;
}
