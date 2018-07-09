package pl.noip.lolstats.lol.stats.Exceptions;

public class BearerNotPresentException extends JwtException {

    public BearerNotPresentException(){
        super("bearer not present in Authorization header");
    }

    public BearerNotPresentException(String messege){
        super(messege);
    }
}