package com.cyb.ewallet.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TxnType")
public class TxnType {
	
	@Id
	@Column(name="txnTypeId")
	String txnTypeId;
	

	
	 @OneToMany(targetEntity=Transaction.class ,mappedBy="txnType",cascade={CascadeType.ALL})
	 private List<Transaction> transactions;
	
	
	@Column(name="txnType")
	String txnType;
	
	

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getTxnTypeId() {
		return txnTypeId;
	}

	public void setTxnTypeId(String txnTypeId) {
		this.txnTypeId = txnTypeId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	

	
	
	
	
}
