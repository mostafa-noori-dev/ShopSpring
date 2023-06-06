package test.shop.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.shop.app.config.filters.JwtRequestFilter;

import javax.servlet.FilterRegistration;

@Configuration
public class JwtFilterConfig {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public FilterRegistrationBean jwtFilterRegister(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(jwtRequestFilter);
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setName("jwtFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
