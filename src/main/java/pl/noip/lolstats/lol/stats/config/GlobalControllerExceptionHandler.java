package pl.noip.lolstats.lol.stats.config;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.noip.lolstats.lol.stats.dto.ErrorResponse;
import pl.noip.lolstats.lol.stats.utils.NoBearerException;

@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleThrowable(Throwable ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("Error occured, we are working on that"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("token expired"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> handleIMalformedJwtException(MalformedJwtException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("invalid token"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoBearerException.class)
    public ResponseEntity<?> handleNoBearerException(NoBearerException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("expected bearer authorization type"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("Authorization header not present"), HttpStatus.BAD_REQUEST);

    }
}