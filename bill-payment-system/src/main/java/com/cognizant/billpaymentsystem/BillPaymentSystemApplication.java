package com.cognizant.billpaymentsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 810512
 *
 */

@Configuration
@SpringBootApplication
public class BillPaymentSystemApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(BillPaymentSystemApplication.class, args);
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
    }

}
