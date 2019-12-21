package com.cognizant.billpaymentsystem.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.billpaymentsystem.model.User;
import com.cognizant.billpaymentsystem.model.Vendor;
import com.cognizant.billpaymentsystem.model.VendorType;

/**
 * @author 810512
 *
 */

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer>{
	Vendor findByVendorName(String vendorName);
	Vendor findByVendorId(int vendorId);
	List<Vendor> findVendorByVendorFlag(int flag);
	List<Vendor> findVendorByVendorTypeAndUser(VendorType vendorType,User user);
}
