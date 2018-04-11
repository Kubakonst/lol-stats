package pl.noip.lolstats.lol.stats.config;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.noip.lolstats.lol.stats.dto.ErrorResponse;

@ControllerAdvice
public class TokenExceptionHandler {
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("token expired"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
