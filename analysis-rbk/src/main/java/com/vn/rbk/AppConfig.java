package com.vn.rbk;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class AppConfig {
    @Value("${rbk.url}")
    private String url;
    @Value("${rbk.chotkq}")
    private String chotkq;
    @Value("${rbk.trendURL}")
    private String trendURL;
    @Value("${rbk.caudepURL}")
    private String caudepURL;
}
