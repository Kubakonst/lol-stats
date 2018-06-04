package pl.noip.lolstats.lol.stats.dto;


import lombok.Getter;


@Getter
public class ErrorResponse {
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public ErrorResponse() {
    }


}
