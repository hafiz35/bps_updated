/**
 * User Model
 */
package com.cognizant.billpaymentsystem.model;

import java.util.List;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author 810512
 *
 */

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="us_id")
	int userId;
	
	@Column(name="us_user_name")
	String username;
	@Column(name="us_first_name")
	String firstname;
	@Column(name="us_last_name")
	String lastname;
	@Column(name="us_age")
	int age;
	@Column(name="us_gender")
	String gender;
	@Column(name="us_contact_number")
	String contactNumber;
	@Column(name="us_aadhar_number")
	String aadharNumber;
	@Column(name="us_pan")
	String pan;
	@Column(name="us_password")
	String password;
	
	@Transient
	boolean admin=false;

	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="ro_id")
	Role role;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name="user_has_vendor",
		joinColumns=@JoinColumn(name="user_us_id"),
		inverseJoinColumns=@JoinColumn(name="vendor_v_id")
		)
	Set<Vendor> vendor;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@JsonIgnore
	List<Bill> bill;

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", age=" + age + ", gender=" + gender + ", contactNumber=" + contactNumber
				+ ", aadharNumber=" + aadharNumber + ", pan=" + pan + ", password=" + password + ", role=" + role
				+ ", vendor=" + vendor + "]";
	}


	public Set<Vendor> getVendor() {
		return vendor;
	}


	public void setVendor(Set<Vendor> vendor) {
		this.vendor = vendor;
	}


	public User() {
		super();
	}


	public User(int userId, String username, String firstname, String lastname, int age, String gender,
			String contactNumber, String aadharNumber, String pan, String password, Role role) {
		super();
		this.userId = userId;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.aadharNumber = aadharNumber;
		this.pan = pan;
		this.password = password;
		this.role = role;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getAadharNumber() {
		return aadharNumber;
	}


	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}


	public String getPan() {
		return pan;
	}


	public void setPan(String pan) {
		this.pan = pan;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}
	
	public List<Bill> getBill() {
		return bill;
	}

	public void setBill(List<Bill> bill) {
		this.bill = bill;
	}
	
}
