/**
 * 
 */
package com.cognizant.billpaymentsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.billpaymentsystem.model.Vendor;
import com.cognizant.billpaymentsystem.model.VendorType;

/**
 * @author 810512
 *
 */
public interface VendorTypeRepository extends JpaRepository<VendorType, Integer>{
	VendorType findByVendorTypeName(String vendorTypeName);
}
