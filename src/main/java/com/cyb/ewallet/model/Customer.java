package com.cyb.ewallet.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cardNumber")
	private String cardNumber;
	
	
	@Column(name = "empId",unique=true,nullable=false)
	private Long empId;
	
	
	@Column(name = "firstName",nullable=false)
	private String firstName;
	 
	 @Column(name = "lastName")
	private String lastName;
	 
	 @Column(name = "bBalance")
	private Double bBalance;
	 
	 @Column(name = "lastTxnTimeStamp")
	private Timestamp lastTxnTimeStamp;
	 
	 @Column(name = "pin")
	private String pin;
	 
	 @Column(name = "activeStatus")
	private Boolean activeStatus;
	 
	 @Column(name = "organization")
	private String organization;
	 
	 @Column(name = "emailId")
	private String emailId;
	 
	 @Column(name = "mobileNo")
	private String mobileNo;
	 
	 @Column(name = "activeDays")
	private String activeDays;
	 
	 @OneToMany(targetEntity=Transaction.class ,mappedBy="customer",cascade={CascadeType.ALL})
	 private List<Transaction> transactions;
	 
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
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
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public Double getbBalance() {
		return bBalance;
	}
	public void setbBalance(Double bBalance) {
		this.bBalance = bBalance;
	}
	public Timestamp getLastTxnTimeStamp() {
		return lastTxnTimeStamp;
	}
	public void setLastTxnTimeStamp(Timestamp lastTxnTimeStamp) {
		this.lastTxnTimeStamp = lastTxnTimeStamp;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public Boolean getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getActiveDays() {
		return activeDays;
	}
	public void setActiveDays(String activeDays) {
		this.activeDays = activeDays;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
}

