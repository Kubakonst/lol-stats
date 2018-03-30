package pl.noip.lolstats.lol.stats.dto;

public class TokenResponde {

   private String cleanToken;

    public TokenResponde (String cleanToken){
        this.cleanToken=cleanToken;
    }

    public TokenResponde(){

    }

    public String getCleanToken() {
        return cleanToken;
    }

    public void setCleanToken(String cleanToken) {
        this.cleanToken = cleanToken;
    }
}
