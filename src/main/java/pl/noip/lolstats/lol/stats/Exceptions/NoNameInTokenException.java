package pl.noip.lolstats.lol.stats.Exceptions;

public class NoNameInTokenException extends JwtException {

    public NoNameInTokenException() {
        super("there is no name in token");
    }

    public NoNameInTokenException(String messege) {
        super(messege);
    }
}