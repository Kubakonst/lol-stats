package pl.noip.lolstats.lol.stats.dto;

import org.hibernate.validator.constraints.NotBlank;

public class SummonerNameRequest {

    @NotBlank(message = "put some Summoner Name")
    private String sumName;

    public String getSumName() {
        return sumName;
    }

    public void setSumName(String sumName) {
        this.sumName = sumName;
    }
}
