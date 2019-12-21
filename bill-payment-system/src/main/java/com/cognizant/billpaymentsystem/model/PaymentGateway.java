/**
 * PaymentGateway Model
 */
package com.cognizant.billpaymentsystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 810512
 *
 */

@Entity
@Table(name="payment_gateway")
public class PaymentGateway {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pg_id")
	int paymentGatewayId;
	
	@Column(name="pg_name")
	String paymentGatewayName;
	
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name="vendor_has_payment_gateway",
			joinColumns=@JoinColumn(name="payment_gateway_pg_id"),
			inverseJoinColumns=@JoinColumn(name="vendor_v_id"))
	List<Vendor> vendor;

	public PaymentGateway() {
		super();
	}

	public PaymentGateway(int paymentGatewayId, String paymentGatewayName) {
		super();
		this.paymentGatewayId = paymentGatewayId;
		this.paymentGatewayName = paymentGatewayName;
	}

	public int getPaymentGatewayId() {
		return paymentGatewayId;
	}

	public void setPaymentGatewayId(int paymentGatewayId) {
		this.paymentGatewayId = paymentGatewayId;
	}

	public String getPaymentGatewayName() {
		return paymentGatewayName;
	}

	public void setPaymentGatewayName(String paymentGatewayName) {
		this.paymentGatewayName = paymentGatewayName;
	}

	public List<Vendor> getVendor() {
		return vendor;
	}

	public void setVendor(List<Vendor> vendor) {
		this.vendor = vendor;
	}

}
