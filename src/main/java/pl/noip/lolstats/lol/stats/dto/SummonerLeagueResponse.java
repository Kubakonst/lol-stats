package pl.noip.lolstats.lol.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SummonerLeagueResponse {

    private Set<SummonerLeague> ss;
}