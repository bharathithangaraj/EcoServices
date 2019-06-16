package com.unimoni.oauth.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(unique = true)
	private String userName;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private int isActive;
	private int isEmailVerified;
	private int isGuest;
	private Date createdOn;
	private Date modifiedOn;
	@Column(unique = true)
	private String userIdentity; 
	
	
	

	public User() {
		super();
	}

	
	public User( String userName, String password, String firstName, String middleName, String lastName,
			String email, String mobileNumber, int isActive, int isEmailVerified, int isGuest, Date createdOn,
			Date modifiedOn, String userIdentity) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.isActive = isActive;
		this.isEmailVerified = isEmailVerified;
		this.isGuest = isGuest;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.userIdentity = userIdentity;
	}


	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public int getIsActive() {
		return isActive;
	}

	public int getIsEmailVerified() {
		return isEmailVerified;
	}

	public int getIsGuest() {
		return isGuest;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public void setIsEmailVerified(int isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public void setIsGuest(int isGuest) {
		this.isGuest = isGuest;
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



//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
//		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
//		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
//		return result;
//	}


//	@Override
//	public String toString() {
//		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstName="
//				+ firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", email=" + email
//				+ ", mobileNumber=" + mobileNumber + ", isActive=" + isActive + ", isEmailVerified=" + isEmailVerified
//				+ ", isGuest=" + isGuest + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", userIdentity="
//				+ userIdentity + "]";
//	}
	
	

}
