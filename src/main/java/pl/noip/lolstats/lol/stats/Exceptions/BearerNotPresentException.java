package pl.noip.lolstats.lol.stats.Exceptions;

public class BearerNotPresentException extends JwtException {

    public BearerNotPresentException() {
        super("expected bearer authorization type");
    }

    public BearerNotPresentException(String messege) {
        super(messege);
    }
}