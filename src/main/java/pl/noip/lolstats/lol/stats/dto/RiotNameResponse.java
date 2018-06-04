package pl.noip.lolstats.lol.stats.dto;

import lombok.Getter;

import java.util.List;


@Getter
public class RiotNameResponse {

    private List<String> regions;

    public RiotNameResponse(List<String> regions) {
        this.regions = regions;
    }

    public RiotNameResponse() {
    }


}