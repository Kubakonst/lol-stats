package pl.noip.lolstats.lol.stats.utils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import pl.noip.lolstats.lol.stats.model.Account;
public class ResponseEntiry {
    @RequestMapping("/handle")
    public ResponseEntity<String> handle(Account account) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }
}