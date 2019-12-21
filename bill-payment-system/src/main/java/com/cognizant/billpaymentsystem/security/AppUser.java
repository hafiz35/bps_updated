/**
 * 
 */
package com.cognizant.billpaymentsystem.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.billpaymentsystem.model.User;

/**
 * @author 810512
 *
 */
public class AppUser implements UserDetails{

	private User user;
	private List<GrantedAuthority> authorities;
	
	
	public AppUser() {
		super();
	}
	public AppUser(User user) {
		super();
		this.user = user;
		this.authorities=new ArrayList<GrantedAuthority>();
		this.authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
