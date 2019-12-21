/**
 * 
 */
package com.cognizant.billpaymentsystem.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.billpaymentsystem.model.Bill;
import com.cognizant.billpaymentsystem.model.Payment;
import com.cognizant.billpaymentsystem.model.User;
import com.cognizant.billpaymentsystem.model.Vendor;
import com.cognizant.billpaymentsystem.repository.BillRepository;
import com.cognizant.billpaymentsystem.repository.UserRepository;
import com.cognizant.billpaymentsystem.repository.VendorRepository;

/**
 * @author 810512
 *
 */

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	VendorRepository vendorRepository;
	
	@Autowired
	UserRepository userRepository;
	
	/* (non-Javadoc)
	 * @see com.cognizant.billpaymentsystem.service.PaymentService#getPaymentDetails(java.lang.String, java.lang.String)
	 */
	@Override
	public Bill getPaymentDetails(String vendorName, String username,String billPaymentGateway) {
		User user=userRepository.findByUsername(username);
		Vendor vendor=vendorRepository.findByVendorName(vendorName);
		List<Bill> billList=billRepository.findByVendorAndUserAndBillpaymentGateway(vendor, user, billPaymentGateway);
		System.out.println(billList.size());
		for(Bill bl:billList) {
			System.out.println(bl.getNameOnCard());
		}
		Bill bill=new Bill();
		bill=billList.get(billList.size()-1);
		return bill;
	}

	@Override
	public void doPayment(Payment payment) {
		Vendor vendor=vendorRepository.findByVendorName(payment.getVendorName());
		User user=userRepository.findByUsername(payment.getUsername());
		Bill bill=new Bill();
		bill.setUser(user);
		bill.setVendor(vendor);
		bill.setBillAmount(payment.getBillAmount());
		bill.setBillpaymentGateway(payment.getBillpaymentGateway());
		bill.setMonthsPaid(payment.getMonthsPaid());
		bill.setNameOnCard(payment.getNameOnCard());
		bill.setCardNumber(payment.getCardNumber());
		bill.setExpirationMonth(payment.getExpirationMonth());
		bill.setExpirationYear(payment.getExpirationYear());
		bill.setPostalCode(payment.getPostalCode());
		bill.setEmail(payment.getEmail());
		bill.setMobileNumber(payment.getMobileNumber());
		bill.setDateOfPay(new Date());
		billRepository.save(bill);
	}

	@Override
	public List<Bill> getPaymentDetailsOfUser(String vendorName, String username) {
		User user=userRepository.findByUsername(username);
		Vendor vendor=vendorRepository.findByVendorName(vendorName);
		List<Bill> billList=billRepository.findByVendorAndUser(vendor, user);
		return billList;
	}

}
