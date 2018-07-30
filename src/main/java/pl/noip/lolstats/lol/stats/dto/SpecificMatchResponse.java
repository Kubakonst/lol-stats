package pl.noip.lolstats.lol.stats.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SpecificMatchResponse {
    private List<ParticipantIdentities> ParticipantIdentities;
    private List<Participants> Participants;
}
