package com.cyb.ewallet.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.ewallet.model.Transaction;

@Repository("transactionDao")
public class TransactionDaoImpl implements TransactionDao {

	private static final Logger logger = LoggerFactory.getLogger(TransactionDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	
	public void saveTransaction(Transaction txn) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(txn);
	}


	public Transaction findTransactionById(String id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		Transaction transaction = (Transaction) session.load(Transaction.class, id);
		logger.info("Person loaded successfully, Person details="+transaction);
		return transaction;
	}


	public void deleteTransactionById(String id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Transaction transaction = (Transaction) session.load(Transaction.class, id);
		if(null != transaction){
			session.delete(transaction);
		}
		logger.info("transaction deleted successfully, transaction details="+transaction);
	}


	public List<Transaction> findAllTransactions() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Transaction> transactionList = session.createQuery("from Transaction").list();
		for(Transaction p : transactionList){
			logger.info("Transaction List::"+p);
		}
		return transactionList;
	}


	public void deleteAllTransactions() {
		// TODO Auto-generated method stub
		for(Transaction transaction:findAllTransactions()){
			deleteTransactionById(transaction.getTxnId());
		}
	}


	public boolean isTransactionExist(Transaction transaction) {
		// TODO Auto-generated method stub
		Transaction txn=findTransactionById(transaction.getTxnId());
		if(txn.equals(transaction)){
			return true;
		}
		else{
			return false;
		}
	}




}
