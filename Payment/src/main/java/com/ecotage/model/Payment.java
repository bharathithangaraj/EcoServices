package com.ecotage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Payment")
public class Payment {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	@Column(nullable = false)
	private String paymentType;
	
	private Long userId;
	private Long orderId;
	
	
	@Column(nullable = false)
	private int status;
	@Column(nullable = false)
	private Date createdOn;
	@Column(nullable = false)
	private Date modifiedOn;
	
	
	public Payment() {
		super();
	}
	
	public Payment(String paymentType, Long userId, Long orderId, int status,
			Date createdOn, Date modifiedOn) {
		super();
		this.paymentType = paymentType;
		this.userId = userId;
		this.orderId = orderId;
		this.status = status;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}
	
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	
	
	
	

}
