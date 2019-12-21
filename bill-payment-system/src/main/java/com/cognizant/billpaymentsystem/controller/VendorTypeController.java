/**
 * 
 */
package com.cognizant.billpaymentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.billpaymentsystem.model.VendorDetails;
import com.cognizant.billpaymentsystem.service.VendorTypeService;

/**
 * @author 810512
 *
 */

@RestController
@RequestMapping("/vendor-types")
public class VendorTypeController {
	
	@Autowired
	VendorTypeService vendorTypeService;
	
	@GetMapping
	public List<VendorDetails> getAllVendorTypes(){
		return vendorTypeService.getAllVendorTypes();
	}
	
}
