package pl.noip.lolstats.lol.stats.dto;

public class ErrorResponse {
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public ErrorResponse() {
    }

    public String getError() {
        return error;
    }
}
