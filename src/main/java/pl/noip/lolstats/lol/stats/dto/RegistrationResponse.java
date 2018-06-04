package pl.noip.lolstats.lol.stats.dto;


import lombok.Getter;

@Getter
public class RegistrationResponse {

    private String status;

    public RegistrationResponse(String status) {
        this.status = status;
    }

    public RegistrationResponse() {
    }


}
