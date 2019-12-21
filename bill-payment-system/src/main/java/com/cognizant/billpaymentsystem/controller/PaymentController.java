/**
 * 
 */
package com.cognizant.billpaymentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.billpaymentsystem.model.Bill;
import com.cognizant.billpaymentsystem.model.Payment;
import com.cognizant.billpaymentsystem.service.PaymentService;

/**
 * @author 810512
 *
 */

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/{vendorname}/{username}/{billPaymentGateway}")
	public Bill getPaymentDetails(@PathVariable String vendorname,@PathVariable String username,@PathVariable String billPaymentGateway) {
		return paymentService.getPaymentDetails(vendorname, username, billPaymentGateway);
	}
	
	@GetMapping("/{vendorname}/{username}")
	public List<Bill> getPaymentDetailsOfUser(@PathVariable String vendorname,@PathVariable String username) {
		return paymentService.getPaymentDetailsOfUser(vendorname, username);
	}
	
	@PostMapping
	public void doPayment(@RequestBody Payment payment) {
		paymentService.doPayment(payment);
	}
	
}
