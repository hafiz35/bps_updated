/**
 * 
 */
package com.cognizant.billpaymentsystem.model;

import java.util.Date;
import java.util.List;

/**
 * @author 810512
 *
 */
public class VendorDetails {
	
	String vendorName;
	String vendorRegNo;
	String vendortype;
	String password;
	String vendorAddress;
	String vendorCountry;
	String vendorState;
	String vendorEmail;
	String vendorContactNo;
	String vendorWebSite;
	String vendorImage;
	Date vendorCertificationIssueDate;
	Date vendorCertificationValidityDate;
	int vendorYearOfEstablishment;
	int flag;

	List<String> paymentgateway;

	public VendorDetails() {
		super();
	}


	public VendorDetails(String vendorName, String vendorRegNo, String vendortype, String password,
			String vendorAddress, String vendorCountry, String vendorState, String vendorEmail, String vendorContactNo,
			String vendorWebSite, Date vendorCertificationIssueDate, Date vendorCertificationValidityDate,
			int vendorYearOfEstablishment, List<String> paymentgateway) {
		super();
		this.vendorName = vendorName;
		this.vendorRegNo = vendorRegNo;
		this.vendortype = vendortype;
		this.password = password;
		this.vendorAddress = vendorAddress;
		this.vendorCountry = vendorCountry;
		this.vendorState = vendorState;
		this.vendorEmail = vendorEmail;
		this.vendorContactNo = vendorContactNo;
		this.vendorWebSite = vendorWebSite;
		this.vendorCertificationIssueDate = vendorCertificationIssueDate;
		this.vendorCertificationValidityDate = vendorCertificationValidityDate;
		this.vendorYearOfEstablishment = vendorYearOfEstablishment;
		this.paymentgateway = paymentgateway;
	}


	public String getVendorName() {
		return vendorName;
	}


	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}


	public String getVendorRegNo() {
		return vendorRegNo;
	}


	public void setVendorRegNo(String vendorRegNo) {
		this.vendorRegNo = vendorRegNo;
	}


	public String getVendortype() {
		return vendortype;
	}


	public void setVendortype(String vendortype) {
		this.vendortype = vendortype;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getVendorAddress() {
		return vendorAddress;
	}


	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}


	public String getVendorCountry() {
		return vendorCountry;
	}


	public void setVendorCountry(String vendorCountry) {
		this.vendorCountry = vendorCountry;
	}


	public String getVendorState() {
		return vendorState;
	}


	public void setVendorState(String vendorState) {
		this.vendorState = vendorState;
	}


	public String getVendorEmail() {
		return vendorEmail;
	}


	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}


	public String getVendorContactNo() {
		return vendorContactNo;
	}


	public void setVendorContactNo(String vendorContactNo) {
		this.vendorContactNo = vendorContactNo;
	}


	public String getVendorWebSite() {
		return vendorWebSite;
	}
	
	public String getVendorImage() {
		return vendorImage;
	}


	public void setVendorImage(String vendorImage) {
		this.vendorImage = vendorImage;
	}

	public void setVendorWebSite(String vendorWebSite) {
		this.vendorWebSite = vendorWebSite;
	}


	public Date getVendorCertificationIssueDate() {
		return vendorCertificationIssueDate;
	}


	public void setVendorCertificationIssueDate(Date vendorCertificationIssueDate) {
		this.vendorCertificationIssueDate = vendorCertificationIssueDate;
	}


	public Date getVendorCertificationValidityDate() {
		return vendorCertificationValidityDate;
	}


	public void setVendorCertificationValidityDate(Date vendorCertificationValidityDate) {
		this.vendorCertificationValidityDate = vendorCertificationValidityDate;
	}


	public int getVendorYearOfEstablishment() {
		return vendorYearOfEstablishment;
	}


	public void setVendorYearOfEstablishment(int vendorYearOfEstablishment) {
		this.vendorYearOfEstablishment = vendorYearOfEstablishment;
	}

	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public List<String> getPaymentgateway() {
		return paymentgateway;
	}
	public void setPaymentgateway(List<String> paymentgateway) {
		this.paymentgateway = paymentgateway;
	}
	
}
