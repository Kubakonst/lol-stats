package pl.noip.lolstats.lol.stats.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pl.noip.lolstats.lol.stats.Exceptions.BearerNotPresentException;
import pl.noip.lolstats.lol.stats.jwt.JwtChecker;
import pl.noip.lolstats.lol.stats.jwt.TokenSplit;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthFilter implements HandlerInterceptor {

    private JwtChecker jwtChecker;
    private TokenSplit tokenSplit;

    public AuthFilter(JwtChecker jwtChecker, TokenSplit tokenSplit) {
        this.jwtChecker = jwtChecker;
        this.tokenSplit = tokenSplit;

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        HttpServletRequest req = (HttpServletRequest) request;
        String bearer = req.getHeader("Authorization");

        if (bearer == null) {
            log.error("there is no bearer");
            throw new BearerNotPresentException();
        }

        String token = tokenSplit.splitToken(bearer);

        jwtChecker.checkToken(token);

        log.info(
                "it's alive");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}