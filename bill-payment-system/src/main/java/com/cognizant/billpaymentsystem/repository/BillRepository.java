/**
 * 
 */
package com.cognizant.billpaymentsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.billpaymentsystem.model.Bill;
import com.cognizant.billpaymentsystem.model.User;
import com.cognizant.billpaymentsystem.model.Vendor;

/**
 * @author 810512
 *
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>{
	List<Bill> findByVendorAndUserAndBillpaymentGateway(Vendor vendor,User user,String billPaymentGateway); 
	List<Bill> findByVendorAndUser(Vendor vendor,User user);
}
