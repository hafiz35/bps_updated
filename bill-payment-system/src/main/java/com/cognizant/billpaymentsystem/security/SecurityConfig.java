package com.cognizant.billpaymentsystem.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 810512
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	public static Logger LOGGER = (Logger) LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	AppUserDetailService appUserDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(appUserDetailService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		LOGGER.info("Start");
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors();
		httpSecurity.csrf().disable()
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers("/authenticate")
		.permitAll()
		.antMatchers("/vendor/**")
		.permitAll()
		.antMatchers("/payment/**")
		.permitAll()
		.antMatchers("/vendor-types/**")
		.permitAll()
		.antMatchers("/issue/**")
		.permitAll()
		.antMatchers("/users/**")
		.anonymous()
		.anyRequest()
		.authenticated()
		.and().addFilter(new JwtAuthorizationFilter(authenticationManager()));
	} 
	
}
