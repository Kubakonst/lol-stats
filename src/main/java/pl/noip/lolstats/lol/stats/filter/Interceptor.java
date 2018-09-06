package pl.noip.lolstats.lol.stats.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.noip.lolstats.lol.stats.jwt.BearerTokenSeparator;
import pl.noip.lolstats.lol.stats.jwt.JwtChecker;

@Configuration
public class Interceptor extends WebMvcConfigurerAdapter {

    private JwtChecker jwtChecker;
    private BearerTokenSeparator bearerTokenSeparator;

    public Interceptor(JwtChecker jwtChecker, BearerTokenSeparator bearerTokenSeparator) {
        this.jwtChecker = jwtChecker;
        this.bearerTokenSeparator = bearerTokenSeparator;

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthFilter(jwtChecker, bearerTokenSeparator))
                .addPathPatterns("/api/summoner/name")
                .addPathPatterns("/api/auth/checkToken")
                .addPathPatterns("/api/stats/summoner/name")
                .addPathPatterns("/api/stats/summoner/basicInfo")
                .addPathPatterns("/api/stats/summoner/matches")
                .addPathPatterns("/api/stats/summoner/league");
    }
}
