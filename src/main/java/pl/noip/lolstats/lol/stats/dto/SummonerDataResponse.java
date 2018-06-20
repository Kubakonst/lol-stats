package pl.noip.lolstats.lol.stats.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SummonerDataResponse {

    private String summonerLevel;
    private String accountId;
    private String id;
    private String profileIconId;
}
