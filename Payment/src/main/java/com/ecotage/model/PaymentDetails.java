package com.ecotage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PaymentDetails")
public class PaymentDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentDetailId;

	
	private String CardHolderName;
	private String cardNo;
	private String expDate;
	private int cvv;
	private double price;
	
	@Column(nullable = false)
	private int status;
	@Column(nullable = false)
	private Date createdOn;
	@Column(nullable = false)
	private Date modifiedOn;
	private Long paymentId;
	
	
	
	public PaymentDetails() {
		super();
	}
	
	public PaymentDetails( String cardHolderName, String cardNo, String expDate, int cvv, double price,
			int status, Date createdOn, Date modifiedOn, Long paymentId) {
		super();
		CardHolderName = cardHolderName;
		this.cardNo = cardNo;
		this.expDate = expDate;
		this.cvv = cvv;
		this.price = price;
		this.status = status;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.paymentId = paymentId;
	}
	
	public Long getPaymentDetailId() {
		return paymentDetailId;
	}
	public void setPaymentDetailId(Long paymentDetailId) {
		this.paymentDetailId = paymentDetailId;
	}
	
	public String getCardHolderName() {
		return CardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		CardHolderName = cardHolderName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	
}
