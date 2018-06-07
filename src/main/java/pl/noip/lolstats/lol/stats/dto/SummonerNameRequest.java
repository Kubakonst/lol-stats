package pl.noip.lolstats.lol.stats.dto;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;


@Getter
@Setter
public class SummonerNameRequest {

    @NotBlank(message = "put some Summoner Name")
    private String sumName;
    @NotBlank(message = "choose any region")
    private String region;


}