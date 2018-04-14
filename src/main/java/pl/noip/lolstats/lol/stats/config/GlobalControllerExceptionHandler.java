package pl.noip.lolstats.lol.stats.config;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;
import pl.noip.lolstats.lol.stats.dto.ErrorResponse;

@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleThrowable(Throwable ex, WebRequest request) {
        ex.printStackTrace(); // todo change to log in future
        return new ResponseEntity<>(new ErrorResponse("Error occurred, we are working on that"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("token expired"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> handleIMalformedJwtException(MalformedJwtException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("invalid token"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BearerNotPresentException.class)
    public ResponseEntity<?> handleNoBearerException(BearerNotPresentException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("expected bearer authorization type"), HttpStatus.UNAUTHORIZED);
    }

}
