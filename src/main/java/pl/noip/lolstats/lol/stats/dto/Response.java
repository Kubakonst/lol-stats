package pl.noip.lolstats.lol.stats.dto;

public class Response {
    private int sum;

    public Response() {
    }

    public Response(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }
}