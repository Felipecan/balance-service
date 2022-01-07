package com.example.BalanceService.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Configuration
@ConfigurationProperties
public class Properties {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class App {
		
		private String name;
	}
	
	private final App app = new App();
}