package com.MyMicroservices.limitsserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-server")
@Setter
@Getter
public class LimitConfig {
    private int minimum;
    private int maximum;
}
