package com.unimoni.oauth.request.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.unimoni.oauth.model.enums.AddressType;
import com.unimoni.oauth.model.enums.Gender;

public class UserDetailReq extends UserReq {

	private static final long serialVersionUID = 1L;

	private Long userDetailId;


	private String name;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String location;
	private String houseNo;
	private String address1;
	private String address2;
	private String mobileNo;
	private String city;
	private String State;
	private int pincode;
	private String landMark;
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
	private Boolean isPrimaryAddress;
	private Date createdOn;
	private Date modifiedOn;
	private String userIdentity;
	private String firstName;
	private String lastName;

	public UserDetailReq() {
		super();
	}

	public UserDetailReq(Long userDetailId, String name, Gender gender, String location, String houseNo,
			String address1, String address2, String mobileNo, String city, String state, int pincode, String landMark,
			AddressType addressType, Boolean isPrimaryAddress, Date createdOn, Date modifiedOn) {
		super();
		this.userDetailId = userDetailId;
		this.name = name;
		this.gender = gender;
		this.location = location;
		this.houseNo = houseNo;
		this.address1 = address1;
		this.address2 = address2;
		this.mobileNo = mobileNo;
		this.city = city;
		State = state;
		this.pincode = pincode;
		this.landMark = landMark;
		this.addressType = addressType;
		this.isPrimaryAddress = isPrimaryAddress;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}
	
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getUserDetailId() {
		return userDetailId;
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public String getLocation() {
		return location;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return State;
	}

	public int getPincode() {
		return pincode;
	}

	public String getLandMark() {
		return landMark;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public Boolean getIsPrimaryAddress() {
		return isPrimaryAddress;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setUserDetailId(Long userDetailId) {
		this.userDetailId = userDetailId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		State = state;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public void setIsPrimaryAddress(Boolean isPrimaryAddress) {
		this.isPrimaryAddress = isPrimaryAddress;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}

}