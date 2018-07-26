package pl.noip.lolstats.lol.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SummonerDataResponse {

    private String summonerLevel;
    private String accountId;
    private String id;
    private String profileIconId;
    private String name;
}
