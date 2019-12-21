/**
 * 
 */
package com.cognizant.billpaymentsystem.service;

import java.util.List;

import com.cognizant.billpaymentsystem.model.Vendor;
import com.cognizant.billpaymentsystem.model.VendorDetails;

/**
 * @author 810512
 *
 */
public interface VendorService {
	VendorDetails getVendorByVendorName(String vendorName);
	List<Vendor> getVendorsByFlag(int flag);
	void acceptVendor(int vendorId);
	void declineVendor(int vendorId);
	void sentBackForCorrection(int vendorId);
	void updateVendor(VendorDetails vendorDetails);
	List<VendorDetails> getAllVendorsByVendorType(String vendorType,String username);
}
