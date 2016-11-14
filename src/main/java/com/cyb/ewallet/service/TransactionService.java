package com.cyb.ewallet.service;

import java.util.List;

import com.cyb.ewallet.model.Transaction;

public interface TransactionService {
Transaction findTransactionById(String id);
	void saveTransaction(Transaction transaction);
	List<Transaction> findAllTransactions(); 
	public boolean isTransactionExist(Transaction transaction);
}
