package com.packages.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

enum DeviceType {
	LAPTOP, MOBILE, TV;
}

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	@Column(name="firstName")
	private String firstName;
	
	private String lastName;
	@Column(name="mobileNo")
	private Date mobileNo;
	private String address;
	@Column(name="createdDate")
	@CreationTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date createdDate;
	@Column(name="updatedDate")
	@CreationTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date updatedDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firsName) {
		this.firstName = firsName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Date mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	

	

}
