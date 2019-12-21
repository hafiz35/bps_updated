/**
 * 
 */
package com.cognizant.billpaymentsystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.billpaymentsystem.exception.UserAlreadyExistsException;
import com.cognizant.billpaymentsystem.model.User;
import com.cognizant.billpaymentsystem.model.VendorDetails;
import com.cognizant.billpaymentsystem.model.VendorType;
import com.cognizant.billpaymentsystem.service.UserService;

/**
 * @author 810512
 *
 */

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping
	public User signUp(@RequestBody @Valid User user) throws UserAlreadyExistsException{
		return userService.signUp(user);
	}
	
	@PostMapping("/vendor")
	public VendorType signUp(@RequestBody @Valid VendorDetails vendorDetails) throws UserAlreadyExistsException{
		return userService.signUp(vendorDetails);
	}
	
	@GetMapping("/{username}")
	public boolean checkUserExists(@PathVariable String username) {
		return userService.checkUserExists(username);
	}
	
}
