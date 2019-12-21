/**
 * 
 */
package com.cognizant.billpaymentsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.billpaymentsystem.model.Vendor;
import com.cognizant.billpaymentsystem.model.VendorDetails;
import com.cognizant.billpaymentsystem.model.VendorType;
import com.cognizant.billpaymentsystem.repository.VendorTypeRepository;

/**
 * @author 810512
 *
 */

@Service
public class VendorTypeServiceImpl implements VendorTypeService {

	@Autowired
	VendorTypeRepository vendorTypeRepository;
	
	
	/* (non-Javadoc)
	 * @see com.cognizant.billpaymentsystem.service.VendorTypeService#getAllVendorTypes()
	 */
	@Override
	public List<VendorDetails> getAllVendorTypes() {
		List<VendorType> vendorTypeList=vendorTypeRepository.findAll();
		List<VendorDetails> vendorDetailsList=new ArrayList<VendorDetails>();
		VendorDetails vendorDetails;
		for(VendorType vendorType:vendorTypeList) {
			vendorDetails=new VendorDetails();
			vendorDetails.setVendortype(vendorType.getVendorTypeName());
			vendorDetails.setVendorImage(vendorType.getVendorImage());
			vendorDetailsList.add(vendorDetails);
		}
		return vendorDetailsList;
	}

}
