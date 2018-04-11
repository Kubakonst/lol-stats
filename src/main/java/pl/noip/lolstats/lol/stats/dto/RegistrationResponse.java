package pl.noip.lolstats.lol.stats.dto;

public class RegistrationResponse {

    private String status;

    public RegistrationResponse(String status) {
        this.status = status;
    }

    public RegistrationResponse() {
    }

    public String getStatus() {
        return status;
    }
}
