package com.cyb.ewallet.dao;

import java.util.List;

import com.cyb.ewallet.model.Transaction;

public interface TransactionDao {
	
	Transaction findTransactionById(String id);
	
	void saveTransaction(Transaction transaction);

	List<Transaction> findAllTransactions(); 
	
	
	public boolean isTransactionExist(Transaction transaction);
	
}
