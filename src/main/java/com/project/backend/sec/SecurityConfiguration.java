package com.project.backend.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    JWTFliter jwtFliter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/user").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api/availableFlight").hasRole("PASSENGER")
                .antMatchers(HttpMethod.POST,"/api/ticket").hasRole("PASSENGER")
                .antMatchers(HttpMethod.POST,"/api/voucher").hasRole("PASSENGER")
                .antMatchers(HttpMethod.GET,"/api/voucher/user/**").hasRole("PASSENGER")
                .antMatchers(HttpMethod.GET,"/api/user/**").hasRole("PASSENGER")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT,"/api/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/user/**").hasRole("ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers("/api/ticket/**").hasRole("MANAGER")
                .antMatchers("/api/voucher/**").hasRole("MANAGER")
                .antMatchers("/api/route/**").hasRole("MANAGER")
                .antMatchers("/api/flight/**").hasRole("MANAGER")
                .antMatchers("/api/aircraft/**").hasRole("MANAGER")
                .antMatchers("/api/airport/**").hasRole("MANAGER")
                .antMatchers("/api/model/**").hasRole("MANAGER")
                .antMatchers("/api/country/**").hasRole("MANAGER")
                .antMatchers("/api/promotion/**").hasRole("MANAGER")
                .antMatchers("/api/fi/**").hasRole("MANAGER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFliter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
