/**
 * Vendor Type Model
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 810512
 *
 */

@Entity
@Table(name="vendor_type")
public class VendorType {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vt_id")
	int vendorTypeId;
	
	@Column(name="vt_name")
	String vendorTypeName;
	
	@Column(name="vt_image")
	String vendorImage;

	@OneToMany(mappedBy="vendorType",cascade=CascadeType.ALL)
	@JsonIgnore
	List<Vendor> vendor;

	@Override
	public String toString() {
		return "VendorType [vendorTypeId=" + vendorTypeId + ", vendorTypeName=" + vendorTypeName + ", vendor=" + vendor
				+ "]";
	}

	public VendorType() {
		super();
	}

	public VendorType(int vendorTypeId, String vendorTypeName, List<Vendor> vendors) {
		super();
		this.vendorTypeId = vendorTypeId;
		this.vendorTypeName = vendorTypeName;
		this.vendor = vendors;
	}

	public int getVendorTypeId() {
		return vendorTypeId;
	}

	public void setVendorTypeId(int vendorTypeId) {
		this.vendorTypeId = vendorTypeId;
	}

	public String getVendorTypeName() {
		return vendorTypeName;
	}

	public void setVendorTypeName(String vendorTypeName) {
		this.vendorTypeName = vendorTypeName;
	}
	
	public String getVendorImage() {
		return vendorImage;
	}

	public void setVendorImage(String vendorImage) {
		this.vendorImage = vendorImage;
	}

	public List<Vendor> getVendors() {
		return vendor;
	}

	public void setVendors(List<Vendor> vendors) {
		this.vendor = vendors;
	}
	
}
