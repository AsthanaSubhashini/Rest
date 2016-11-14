package com.cyb.ewallet.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyb.ewallet.dao.TransactionDao;
import com.cyb.ewallet.model.Transaction;

@Service("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionDao transactionDao;
	
	public Transaction findTransactionById(String id) {
		// TODO Auto-generated method stub
		return transactionDao.findTransactionById(id);
	}

	public void saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		transactionDao.saveTransaction(transaction);
	}

	public List<Transaction> findAllTransactions() {
		// TODO Auto-generated method stub
		return transactionDao.findAllTransactions();
	}

	public boolean isTransactionExist(Transaction transaction) {
		// TODO Auto-generated method stub
		return transactionDao.isTransactionExist(transaction);
	}

}
