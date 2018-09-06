package pl.noip.lolstats.lol.stats.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MatchesResponse {

    private List<Match> matches;
}