package com.reto.rest.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("error", "unauthorized");
        responseBody.put("message", "Acceso restringido");
        responseBody.put("code", "401");
        http.exceptionHandling().authenticationEntryPoint(new MyEntryPoint(HttpStatus.UNAUTHORIZED, responseBody)).and()
                .authorizeRequests()
                .anyRequest().authenticated().and().httpBasic().and().csrf().disable();

    }

}