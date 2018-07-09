package pl.noip.lolstats.lol.stats.Exceptions;

public class JwtException extends RuntimeException {

    public JwtException(){
        super("invalid token");
    }

    public JwtException(String messege){
        super(messege);
    }

}