package pl.noip.lolstats.lol.stats.dto;

public class LoginResponse {

    private String accessToken;
    private String bearer;

    public LoginResponse (String accessToken, String bearer){
        this.accessToken = accessToken;
        this.bearer = bearer;
    }

    public LoginResponse(){
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }
}
