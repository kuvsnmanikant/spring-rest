package com.firstspringboot.learningspring.boot.configurations;

import java.io.Serializable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.firstspringboot.learningspring.boot.security.JwtAuthenticationEntryPoint;
import com.firstspringboot.learningspring.boot.security.JwtAuthenticatonFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration implements Serializable {

    private UserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAuthenticatonFilter jwtAuthenticatonFilter;
    
    public SecurityConfiguration(UserDetailsService userDetailsService,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAuthenticatonFilter jwtAuthenticatonFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenticatonFilter = jwtAuthenticatonFilter;
    }

    @Bean  // here we are setting the basic auth and disable form-login
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        
       // here we are giving access to all users to all methods
       // httpSecurity.csrf().disable().authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

       //here we are giving specific access to specific methods based on user roles using basic auth
        // httpSecurity.csrf().disable().authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll().anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

        httpSecurity.csrf().disable().authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/swagger-ui.html/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/webjars/**").permitAll()
        .requestMatchers("/api/auth/**").permitAll()
        .anyRequest().authenticated())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(jwtAuthenticatonFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    // @Bean  // here we are creating multiple users and saving in inmemory
    // public UserDetailsService userDetailsService(){

    //     UserDetails user1 = User.builder().username("first-springboot-app").password(passwordEncoder().encode("first-springboot-app")).roles("USER").build();
    //     UserDetails user2 = User.builder().username("Admin").password(passwordEncoder().encode("Admin@123")).roles("ADMIN").build();
    //     return new InMemoryUserDetailsManager(user1, user2);
    // }
}
