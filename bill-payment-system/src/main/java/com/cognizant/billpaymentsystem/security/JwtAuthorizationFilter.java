package com.cognizant.billpaymentsystem.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.cognizant.billpaymentsystem.BillPaymentSystemConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
/**
 * @author 810512
 *
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		BillPaymentSystemConstant.LOGGER.debug("START");
		BillPaymentSystemConstant.LOGGER.debug("{}: ", authenticationManager);
	}
	
	@Override
	public void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain) throws IOException,ServletException{
		BillPaymentSystemConstant.LOGGER.debug("START");
		String header=req.getHeader("Authorization");
		BillPaymentSystemConstant.LOGGER.debug(header);
		
		if(header==null || !header.startsWith("Bearer ")) {
			BillPaymentSystemConstant.LOGGER.debug("Inside If");
			chain.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication=getAuthentication(req);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
		BillPaymentSystemConstant.LOGGER.debug("END");
		
	}
	
	public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		String token=req.getHeader("Authorization");
		if(token!=null) {
			Jws<Claims> jws;
			try {
				jws=Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token.replace("Bearer",""));
				String user = jws.getBody().getSubject();
				
				if (user != null) {
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}
				
			}catch(JwtException e) {
				return null;
			}
		}
		return null;
	}

}
