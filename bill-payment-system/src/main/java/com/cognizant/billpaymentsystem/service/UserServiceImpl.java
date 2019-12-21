/**
 * 
 */
package com.cognizant.billpaymentsystem.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.billpaymentsystem.exception.UserAlreadyExistsException;
import com.cognizant.billpaymentsystem.model.PaymentGateway;
import com.cognizant.billpaymentsystem.model.User;
import com.cognizant.billpaymentsystem.model.Vendor;
import com.cognizant.billpaymentsystem.model.VendorDetails;
import com.cognizant.billpaymentsystem.model.VendorType;
import com.cognizant.billpaymentsystem.repository.PaymentGatewayRepository;
import com.cognizant.billpaymentsystem.repository.UserRepository;
import com.cognizant.billpaymentsystem.repository.VendorRepository;
import com.cognizant.billpaymentsystem.repository.VendorTypeRepository;
import com.cognizant.billpaymentsystem.security.AppUserDetailService;

/**
 * @author 810512
 *
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	AppUserDetailService appUserDetailService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PaymentGatewayRepository paymentGatewayRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	VendorTypeRepository vendorTypeRepository;
	
	/*
	 * @see com.cognizant.billpaymentsystem.service.UserService#signUp(com.cognizant.billpaymentsystem.model.User)
	 */
	@Override
	public User signUp(User user) throws UserAlreadyExistsException {
		if(user.isAdmin()) {
			System.out.println("Admin : "+user);
			return appUserDetailService.signUp(user, 1);
		}
		else {
			List<Vendor> vendorList=vendorRepository.findAll();
			Set<Vendor> vendorSet=new HashSet<Vendor>();
			for(Vendor vendor:vendorList) {
				vendorSet.add(vendor);
			}
			user.setVendor(vendorSet);
			System.out.println("User : "+user);
			return appUserDetailService.signUp(user,2);
		}
	}
	
	/*
	 * @see com.cognizant.billpaymentsystem.service.UserService#signUp(com.cognizant.billpaymentsystem.model.VendorDetails)
	 */
	@Override
	public VendorType signUp(VendorDetails vendorDetails) throws UserAlreadyExistsException {
		List<PaymentGateway> paymentGatewayList=new ArrayList<PaymentGateway>();
		PaymentGateway paymentGateway;
		for(String pgateway : vendorDetails.getPaymentgateway()) {
			paymentGateway=paymentGatewayRepository.findByPaymentGatewayName(pgateway);
			paymentGatewayList.add(paymentGateway);
		}
		
		
		
		Vendor vendor=new Vendor();
		vendor.setVendorName(vendorDetails.getVendorName());
		vendor.setVendorRegNo(vendorDetails.getVendorRegNo());
		vendor.setVendorAddress(vendorDetails.getVendorAddress());
		vendor.setVendorCountry(vendorDetails.getVendorCountry());
		vendor.setVendorState(vendorDetails.getVendorState());
		vendor.setVendorEmail(vendorDetails.getVendorEmail());
		vendor.setVendorContactNo(vendorDetails.getVendorContactNo());
		vendor.setVendorWebSite(vendorDetails.getVendorWebSite());
		vendor.setVendorCertificationIssueDate(vendorDetails.getVendorCertificationIssueDate());
		vendor.setVendorCertificationValidityDate(vendorDetails.getVendorCertificationValidityDate());
		vendor.setVendorYearOfEstablishment(vendorDetails.getVendorYearOfEstablishment());
		vendor.setPaymentGateway(paymentGatewayList);
		vendor.setVendorFlag(0);
		
		
		VendorType vendorType=vendorTypeRepository.findByVendorTypeName(vendorDetails.getVendortype());
		vendor.setVendorType(vendorType);
		
		vendorRepository.save(vendor);
		
		User user=new User();
		user.setUsername(vendorDetails.getVendorName());
		user.setPassword(vendorDetails.getPassword());
		
		User registeredUser=appUserDetailService.signUp(user,3);
		System.out.println(registeredUser.toString());
		
		return vendorType;
	}

	/*
	 * @see com.cognizant.billpaymentsystem.service.UserService#checkUserExists(java.lang.String)
	 */
	@Override
	public boolean checkUserExists(String username) {
		User user=userRepository.findByUsername(username);
		if(user!=null) {
			return true;
		}
		return false;
	}

	/*
	 * @see com.cognizant.billpaymentsystem.service.UserService#getUserByUsername(java.lang.String)
	 */
	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
