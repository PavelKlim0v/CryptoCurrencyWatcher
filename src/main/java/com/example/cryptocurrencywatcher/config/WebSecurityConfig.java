package com.example.cryptocurrencywatcher.config;

import com.example.cryptocurrencywatcher.entity.User;
import com.example.cryptocurrencywatcher.repository.UserRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.example.cryptocurrencywatcher.constants.Constants.*;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/coin/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepo userRepo) {
        return map -> {
            String id = (String) map.get(SUB);
            User user = userRepo.findById(id).orElseGet(() -> {
                User newUser = new User();
                newUser.setId(id);
                newUser.setName((String) map.get(NAME));
                newUser.setEmail((String) map.get(EMAIL));
                newUser.setLocale((String) map.get(LOCALE));
                newUser.setUserpic((String) map.get(PICTURE));
                return newUser;
            });
            return userRepo.save(user);
        };
    }
}
