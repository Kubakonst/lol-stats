package pl.noip.lolstats.lol.stats.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterRegistration {

    @Bean
    public FilterRegistrationBean filterRegistrationBean()  {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new AuthFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/api/auth/login/*");

        return registrationBean;
    }
}
