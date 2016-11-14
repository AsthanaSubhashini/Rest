package com.cyb.ewallet.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="Transaction")
public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "txnId")
	private String txnId;
	 
	@ManyToOne
	@JoinColumn(name="txnTypeId")
	TxnType txnType;
	
	@ManyToOne
	@JoinColumn(name="vendorId")
	private Vendor vendor;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="error_code")
	TxnError txnError;
	 
	@ManyToOne
	@JoinColumn(name="cardNumber")
	private Customer customer;
	
	@Column(name = "timeStamp")
	private Timestamp timeStamp;
	 
	 @Column(name = "orderId")
	 private Long orderId;
	 
	
	 @Column(name = "txnAmount")
	 private Double txnAmount;
	
	 @Column(name = "currencyType")
	private String currencyType;
	
	 @Column(name = "status")
	private Boolean status;
	public TxnType getTxnType() {
		return txnType;
	}
	public void setTxnType(TxnType txnType) {
		this.txnType = txnType;
	}
	public TxnError getTxnError() {
		return txnError;
	}
	public void setTxnError(TxnError txnError) {
		this.txnError = txnError;
	}
	public String getTxnId() {
			return txnId;
		}	
		public void setTxnId(String txnId) {
			this.txnId = txnId;
		}
		public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Double getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(Double txnAmount) {
		this.txnAmount = txnAmount;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	
	
}
