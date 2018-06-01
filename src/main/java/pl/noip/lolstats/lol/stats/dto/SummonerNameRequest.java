package pl.noip.lolstats.lol.stats.dto;

import org.hibernate.validator.constraints.NotBlank;

public class SummonerNameRequest {

    @NotBlank(message = "put some Summoner Name")
    private String sumName;
    @NotBlank(message = "choose any region")
    private String region;

    public String getSumName() {
        return sumName;
    }

    public void setSumName(String sumName) {
        this.sumName = sumName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
