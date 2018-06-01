package pl.noip.lolstats.lol.stats.dto;

import java.util.List;

public class RiotNameResponse {

    private List<String> regions;

    public RiotNameResponse(List<String> regions) {
        this.regions = regions;
    }

    public RiotNameResponse() {
    }

    public List<String> getRegions() {
        return regions;
    }
}