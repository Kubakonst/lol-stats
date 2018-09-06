package pl.noip.lolstats.lol.stats.Exceptions;

public class MissingTokenPropertyException extends JwtException {

    public MissingTokenPropertyException(String messege) {
        super(messege);
    }
}