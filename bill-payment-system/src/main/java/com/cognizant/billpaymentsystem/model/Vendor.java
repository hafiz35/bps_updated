/**
 * Vendor Model
 */
package com.cognizant.billpaymentsystem.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 810512
 *
 */

@Entity
@Table(name="vendor")
public class Vendor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="v_id")
	int vendorId;
	
	@Column(name="v_name")
	String vendorName;
	@Column(name="v_company_reg_no")
	String vendorRegNo;
	@Column(name="v_address")
	String vendorAddress;
	@Column(name="v_country")
	String vendorCountry;
	@Column(name="v_state")
	String vendorState;
	@Column(name="v_email_address")
	String vendorEmail;
	@Column(name="v_contact_no")
	String vendorContactNo;
	@Column(name="v_web_site")
	String vendorWebSite;
	@Column(name="v_certification_issue_date")
	Date vendorCertificationIssueDate;
	@Column(name="v_certification_validity_date")
	Date vendorCertificationValidityDate;
	@Column(name="v_year_of_establishment")
	int vendorYearOfEstablishment;
	@Column(name="v_flag")
	int vendorFlag;
	
	
	@JsonIgnore
	@ManyToMany(mappedBy="vendor")
	List<User> user;
	
	@ManyToMany
	@JoinTable(name="vendor_has_payment_gateway",
			joinColumns=@JoinColumn(name="vendor_v_id"),
			inverseJoinColumns=@JoinColumn(name="payment_gateway_pg_id"))
	List<PaymentGateway> paymentGateway;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="vendor_type_vt_id")
	VendorType vendorType;
	
	@OneToMany(mappedBy="vendor",cascade=CascadeType.ALL)
	@JsonIgnore
	List<Bill> bill;

	public Vendor() {
		super();
	}

	public VendorType getVendorType() {
		return vendorType;
	}

	public void setVendorType(VendorType vendorType) {
		this.vendorType = vendorType;
	}

	public Vendor(int vendorId, String vendorName, String vendorRegNo, String vendorAddress, String vendorCountry,
			String vendorState, String vendorEmail, String vendorContactNo, String vendorWebSite,
			Date vendorCertificationIssueDate, Date vendorCertificationValidityDate, int vendorYearOfEstablishment,
			int vendorFlag, List<Bill> bill, List<PaymentGateway> paymentGateway) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorRegNo = vendorRegNo;
		this.vendorAddress = vendorAddress;
		this.vendorCountry = vendorCountry;
		this.vendorState = vendorState;
		this.vendorEmail = vendorEmail;
		this.vendorContactNo = vendorContactNo;
		this.vendorWebSite = vendorWebSite;
		this.vendorCertificationIssueDate = vendorCertificationIssueDate;
		this.vendorCertificationValidityDate = vendorCertificationValidityDate;
		this.vendorYearOfEstablishment = vendorYearOfEstablishment;
		this.vendorFlag = vendorFlag;
		this.bill = bill;
		this.paymentGateway = paymentGateway;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
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

	public int getVendorFlag() {
		return vendorFlag;
	}

	public void setVendorFlag(int vendorFlag) {
		this.vendorFlag = vendorFlag;
	}

	public List<Bill> getBill() {
		return bill;
	}

	public void setBill(List<Bill> bill) {
		this.bill = bill;
	}

	public List<PaymentGateway> getPaymentGateway() {
		return paymentGateway;
	}

	public void setPaymentGateway(List<PaymentGateway> paymentGateway) {
		this.paymentGateway = paymentGateway;
	}
	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	
}
