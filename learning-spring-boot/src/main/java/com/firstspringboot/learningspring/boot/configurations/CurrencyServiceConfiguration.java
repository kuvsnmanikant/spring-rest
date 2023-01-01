package com.firstspringboot.learningspring.boot.configurations;
//  this is one way of fetchinig the values from application.properries another one is implemented in security/JwtTokenProvider.java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component   // this will create an instance in spring we will use it by autowiring
@ConfigurationProperties(prefix = "currency-service")
public class CurrencyServiceConfiguration {
    private @Setter@Getter String url;
    private @Setter@Getter String username;
    private @Setter@Getter String key;
}
