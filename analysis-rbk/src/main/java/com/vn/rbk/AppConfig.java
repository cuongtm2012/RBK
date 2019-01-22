package com.vn.rbk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

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
	@Value("${rbk.cau3ngay}")
	private String cau3ngay;
}
