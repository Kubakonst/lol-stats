package pl.noip.lolstats.lol.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SummonerMatchesResponde {

    private List<SingleMatchClass> matches;
}