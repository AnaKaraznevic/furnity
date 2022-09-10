package com.furnity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@Configuration
public class FurnityApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnityApplication.class, args);
	}

}
