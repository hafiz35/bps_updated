/**
 * 
 */
package com.cognizant.billpaymentsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.billpaymentsystem.exception.UserAlreadyExistsException;
import com.cognizant.billpaymentsystem.model.Role;
import com.cognizant.billpaymentsystem.model.User;
import com.cognizant.billpaymentsystem.repository.RoleRepository;
import com.cognizant.billpaymentsystem.repository.UserRepository;



/**
 * @author 810512
 *
 */

@Service
public class AppUserDetailService implements UserDetailsService{
	User user;
	AppUser appUser;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	public AppUserDetailService() {
		super();
	}
	
	public AppUserDetailService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public AppUserDetailService(UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	public User signUp(User newUser,int roleId) throws UserAlreadyExistsException{
		User user=new User();
		System.out.println(newUser.getFirstname());
		user=userRepository.findByUsername(newUser.getUsername());
		System.out.println("user : "+user);
		if(user==null) {
			Role role=roleRepository.findByRoleId(roleId);
			String password=newUser.getPassword();
			newUser.setRole(role);
			System.out.println(newUser.toString());
			newUser.setPassword(passwordEncoder().encode(password));
			userRepository.save(newUser);
			return newUser;
		}
		else {
			throw new UserAlreadyExistsException();
		}
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user=userRepository.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("UserName Not Found");
		else
			appUser=new AppUser(user);
		return appUser;
	}

}
