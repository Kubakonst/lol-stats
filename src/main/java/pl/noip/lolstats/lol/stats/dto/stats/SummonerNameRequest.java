package pl.noip.lolstats.lol.stats.dto.stats;

import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
public class SummonerNameRequest {

    @NotBlank(message = "put some Summoner Name")
    private String sumName;
    @NotBlank(message = "choose any region")
    private String region;
}