package pl.noip.lolstats.lol.stats.dto;

import java.util.List;

public class RiotNameResponse {

    private List regions;

    public RiotNameResponse (List regions) {this.regions = regions; }

    public RiotNameResponse(){
    }

    public List getRegions() {return regions; }
}