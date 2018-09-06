package pl.noip.lolstats.lol.stats.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SummonerBasicInfoResponse {

    private String summonerLevel;
    private String accountId;
    private String id;
    private String profileIconId;
    private String name;
}
