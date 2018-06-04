package pl.noip.lolstats.lol.stats.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginResponse {

    private String accessToken;
    private String bearer;

    public LoginResponse(String accessToken, String bearer) {
        this.accessToken = accessToken;
        this.bearer = bearer;
    }

    public LoginResponse() {
    }


}
