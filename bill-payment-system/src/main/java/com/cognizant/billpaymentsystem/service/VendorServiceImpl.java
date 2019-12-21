/**
 * 
 */
package com.cognizant.billpaymentsystem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.billpaymentsystem.model.PaymentGateway;
import com.cognizant.billpaymentsystem.model.User;
import com.cognizant.billpaymentsystem.model.Vendor;
import com.cognizant.billpaymentsystem.model.VendorDetails;
import com.cognizant.billpaymentsystem.model.VendorType;
import com.cognizant.billpaymentsystem.repository.PaymentGatewayRepository;
import com.cognizant.billpaymentsystem.repository.UserRepository;
import com.cognizant.billpaymentsystem.repository.VendorRepository;
import com.cognizant.billpaymentsystem.repository.VendorTypeRepository;

/**
 * @author 810512
 *
 */

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PaymentGatewayRepository paymentGatewayRepository;
	
	@Autowired
	VendorTypeRepository vendorTypeRepository;
	
	/* (non-Javadoc)
	 * @see com.cognizant.billpaymentsystem.service.VendorService#getVendorsByFlag(int)
	 */
	@Override
	public List<Vendor> getVendorsByFlag(int flag) {
		return vendorRepository.findVendorByVendorFlag(flag);
	}

	
	@Override
	public void acceptVendor(int vendorId) {
		Vendor vendor=vendorRepository.findByVendorId(vendorId);
		vendor.setVendorFlag(1);
		vendorRepository.save(vendor);
		
		List<User> allUsers=userRepository.findAll();
		for(User usr : allUsers) {
			System.out.println(usr.toString());
			if(usr.getRole().getRoleId()==2)
			{	Set<Vendor> usersVendor=usr.getVendor();
				System.out.println("Inside acceptVendor");
				usersVendor.add(vendor);
				usr.setVendor(usersVendor);
				userRepository.save(usr);
			}
		}
	}


	@Override
	public void declineVendor(int vendorId) {
		Vendor vendor=vendorRepository.findByVendorId(vendorId);
		User user=userRepository.findByUsername(vendor.getVendorName());
		userRepository.delete(user);
		vendorRepository.delete(vendor);
		List<User> allUsers=userRepository.findAll(); 
		for(User usr : allUsers) {
			if(usr.getRole().getRoleId()==2)
			{	Set<Vendor> usersVendor=usr.getVendor();
				usersVendor.remove(vendor);
				userRepository.save(usr);
			}
		}
	}


	@Override
	public void sentBackForCorrection(int vendorId) {
		Vendor vendor=vendorRepository.findByVendorId(vendorId);
		vendor.setVendorFlag(2);
		vendorRepository.save(vendor);
	}


	@Override
	public VendorDetails getVendorByVendorName(String vendorName) {
		Vendor vendor=vendorRepository.findByVendorName(vendorName);
		VendorDetails vendorDetails=new VendorDetails();
		vendorDetails.setVendorName(vendorName);
		vendorDetails.setVendorRegNo(vendor.getVendorRegNo());
		vendorDetails.setVendorCountry(vendor.getVendorCountry());
		vendorDetails.setVendorState(vendor.getVendorState());
		vendorDetails.setVendorAddress(vendor.getVendorAddress());
		vendorDetails.setVendorEmail(vendor.getVendorEmail());
		vendorDetails.setVendorContactNo(vendor.getVendorContactNo());
		vendorDetails.setVendorWebSite(vendor.getVendorWebSite());
		vendorDetails.setVendorCertificationIssueDate(vendor.getVendorCertificationIssueDate());
		vendorDetails.setVendorCertificationValidityDate(vendor.getVendorCertificationValidityDate());
		vendorDetails.setVendorYearOfEstablishment(vendor.getVendorYearOfEstablishment());
		vendorDetails.setFlag(vendor.getVendorFlag());
		List<PaymentGateway> paymentGatewayList=paymentGatewayRepository.findPaymentGatewayByVendor(vendor);
		List<String> pGatewayList=new ArrayList<>();
		for(PaymentGateway paymentGateway : paymentGatewayList) {
			pGatewayList.add(paymentGateway.getPaymentGatewayName());
		}
		vendorDetails.setPaymentgateway(pGatewayList);
		return vendorDetails;
	}


	@Override
	public void updateVendor(VendorDetails vendorDetails) {
		List<PaymentGateway> paymentGatewayList=new ArrayList<PaymentGateway>();
		PaymentGateway paymentGateway;
		for(String pgateway : vendorDetails.getPaymentgateway()) {
			paymentGateway=paymentGatewayRepository.findByPaymentGatewayName(pgateway);
			paymentGatewayList.add(paymentGateway);
		}
		
		Vendor vendor=vendorRepository.findByVendorName(vendorDetails.getVendorName());
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
		System.out.println(vendor);
		
		vendorRepository.save(vendor);
		
	}


	@Override
	public List<VendorDetails> getAllVendorsByVendorType(String vendorType,String username) {
		VendorType foundVendorType=vendorTypeRepository.findByVendorTypeName(vendorType);
		User user=userRepository.findByUsername(username);
		List<Vendor> vendorList=vendorRepository.findVendorByVendorTypeAndUser(foundVendorType, user);
		List<VendorDetails> vendorDetailsList=new ArrayList<VendorDetails>();
		for(Vendor vendor:vendorList) {
			VendorDetails vendorDetails=new VendorDetails();
			vendorDetails.setVendorName(vendor.getVendorName());
			vendorDetails.setVendorRegNo(vendor.getVendorRegNo());
			vendorDetails.setVendorCountry(vendor.getVendorCountry());
			vendorDetails.setVendorState(vendor.getVendorState());
			vendorDetails.setVendorAddress(vendor.getVendorAddress());
			vendorDetails.setVendorEmail(vendor.getVendorEmail());
			vendorDetails.setVendorContactNo(vendor.getVendorContactNo());
			vendorDetails.setVendorWebSite(vendor.getVendorWebSite());
			vendorDetails.setVendorCertificationIssueDate(vendor.getVendorCertificationIssueDate());
			vendorDetails.setVendorCertificationValidityDate(vendor.getVendorCertificationValidityDate());
			vendorDetails.setVendorYearOfEstablishment(vendor.getVendorYearOfEstablishment());
			vendorDetails.setFlag(vendor.getVendorFlag());
			List<PaymentGateway> paymentGatewayList=paymentGatewayRepository.findPaymentGatewayByVendor(vendor);
			List<String> pGatewayList=new ArrayList<>();
			for(PaymentGateway paymentGateway : paymentGatewayList) {
				pGatewayList.add(paymentGateway.getPaymentGatewayName());
			}
			vendorDetails.setPaymentgateway(pGatewayList);
			vendorDetailsList.add(vendorDetails);
		}
		return vendorDetailsList;
	}

}
