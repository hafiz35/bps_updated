/**
 * 
 */
package com.cognizant.billpaymentsystem.service;

import java.util.List;

import com.cognizant.billpaymentsystem.model.Bill;
import com.cognizant.billpaymentsystem.model.Payment;

/**
 * @author 810512
 *
 */
public interface PaymentService {
	
	Bill getPaymentDetails(String vendorName,String username,String billPaymentGateway);
	List<Bill> getPaymentDetailsOfUser(String vendorName,String username);
	void doPayment(Payment payment);
}
