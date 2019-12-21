/**
 * 
 */
package com.cognizant.billpaymentsystem.service;

import com.cognizant.billpaymentsystem.exception.UserAlreadyExistsException;
import com.cognizant.billpaymentsystem.model.User;
import com.cognizant.billpaymentsystem.model.VendorType;
import com.cognizant.billpaymentsystem.model.VendorDetails;

/**
 * @author 810512
 *
 */
public interface UserService {
	public User signUp(User user) throws UserAlreadyExistsException;
	public VendorType signUp(VendorDetails vendorDetails) throws UserAlreadyExistsException;
	public boolean checkUserExists(String username);
	public User getUserByUsername(String username);
}