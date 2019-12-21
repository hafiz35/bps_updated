/**
 * 
 */
package com.cognizant.billpaymentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.billpaymentsystem.model.Payment;
import com.cognizant.billpaymentsystem.model.Vendor;
import com.cognizant.billpaymentsystem.model.VendorDetails;
import com.cognizant.billpaymentsystem.service.VendorService;

/**
 * @author 810512
 *
 */

@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	VendorService vendorService;
	
	@GetMapping("/{vendorName}")
	public VendorDetails getVendorByVendorName(@PathVariable String vendorName) {
		return vendorService.getVendorByVendorName(vendorName);
	}
	
	@GetMapping("/vendor-type/{vendorType}/{username}")
	public List<VendorDetails> getAllVendorsByVendorType(@PathVariable String vendorType,@PathVariable String username){
		return vendorService.getAllVendorsByVendorType(vendorType,username);
	}
	
	@GetMapping("/flag/{flagval}")
	List<Vendor> getVendorByFlag(@PathVariable int flagval){
		return vendorService.getVendorsByFlag(flagval);
	}
	
	@PutMapping("/accept/{vendorId}")
	public void acceptVendor(@PathVariable int vendorId) {
		vendorService.acceptVendor(vendorId);
	}
	
	@DeleteMapping("/decline/{vendorId}")
	public void declineVendor(@PathVariable int vendorId) {
		vendorService.declineVendor(vendorId);
	}
	
	@PutMapping("/sentbackforcorrection/{vendorId}")
	public void sentBackForCorrection(@PathVariable int vendorId) {
		vendorService.sentBackForCorrection(vendorId);
	}
	
	@PutMapping("/editvendor")
	public void updateVendor(@RequestBody VendorDetails vendorDetails) {
		vendorService.updateVendor(vendorDetails);
	}
	
}
