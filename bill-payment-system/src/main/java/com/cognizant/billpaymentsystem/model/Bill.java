/**
 * Bill Model
 */
package com.cognizant.billpaymentsystem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 810512
 *
 */

@Entity
@Table(name="bill")
public class Bill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bl_id")
	int bl_id;
	
	@Column(name="bl_payment_gateway")
	String billpaymentGateway; 
	
	@Column(name="bl_amount")
	int billAmount;
	@Column(name="bl_months_paid")
	int monthsPaid;
	@Column(name="bl_name_on_card")
	String nameOnCard;
	@Column(name="bl_card_number")
	int cardNumber;
	@Column(name="bl_expiration_month")
	String expirationMonth;
	@Column(name="bl_expiration_year")
	int expirationYear;
	@Column(name="bl_postal_code")
	int postalCode;
	@Column(name="bl_email")
	String email;
	@Column(name="bl_mobile_number")
	String mobileNumber;
	@Column(name="bl_date_of_pay")
	Date dateOfPay;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="vendor_v_id")
	Vendor vendor;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="user_us_id")
	User user;

	public Bill() {
		super();
	}

	public Bill(int bl_id, String billpaymentGateway, int billAmount, int monthsPaid, String nameOnCard, int cardNumber,
			String expirationMonth, int expirationYear, int postalCode, Vendor vendor, User user) {
		super();
		this.bl_id = bl_id;
		this.billpaymentGateway = billpaymentGateway;
		this.billAmount = billAmount;
		this.monthsPaid = monthsPaid;
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.postalCode = postalCode;
		this.vendor = vendor;
		this.user = user;
	}

	public int getBl_id() {
		return bl_id;
	}

	public void setBl_id(int bl_id) {
		this.bl_id = bl_id;
	}

	public String getBillpaymentGateway() {
		return billpaymentGateway;
	}

	public void setBillpaymentGateway(String billpaymentGateway) {
		this.billpaymentGateway = billpaymentGateway;
	}

	public int getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(int billAmount) {
		this.billAmount = billAmount;
	}

	public int getMonthsPaid() {
		return monthsPaid;
	}

	public void setMonthsPaid(int monthsPaid) {
		this.monthsPaid = monthsPaid;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public int getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Date getDateOfPay() {
		return dateOfPay;
	}

	public void setDateOfPay(Date dateOfPay) {
		this.dateOfPay = dateOfPay;
	}
	
	
}
