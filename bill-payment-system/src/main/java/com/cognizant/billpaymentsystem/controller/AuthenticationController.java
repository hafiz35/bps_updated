/**
 * 
 */
package com.cognizant.billpaymentsystem.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.billpaymentsystem.BillPaymentSystemConstant;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

/**
 * @author 810512
 *
 */

@RestController
public class AuthenticationController {

	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
		System.out.println(authHeader);
		String user=getUser(authHeader);
		BillPaymentSystemConstant.LOGGER.debug(user);
		String role=SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
		Map<String,String> auth=new HashMap<String, String>();
		auth.put("token",generateJwt(user));
		auth.put("role", role);
		return auth;
	}
	
	private String getUser(String authHeader) {
		byte[] auth=Base64.getDecoder().decode(authHeader.split(" ")[1]);
		String authSrc=new String(auth);
		return authSrc;
	}
	
	private String generateJwt(String user) {
		JwtBuilder builder=Jwts.builder();
		builder.setSubject(user);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date(new Date().getTime()+1200000));
		String token=builder.compact();
		return token;
	}
	
}
