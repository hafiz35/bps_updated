/**
 * 
 */
package com.cognizant.billpaymentsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.billpaymentsystem.model.PaymentGateway;
import com.cognizant.billpaymentsystem.model.Vendor;

/**
 * @author 810512
 *
 */
public interface PaymentGatewayRepository extends JpaRepository<PaymentGateway, Integer>{
	PaymentGateway findByPaymentGatewayName(String paymentGatewayname);
	List<PaymentGateway> findPaymentGatewayByVendor(Vendor vendor);
}
