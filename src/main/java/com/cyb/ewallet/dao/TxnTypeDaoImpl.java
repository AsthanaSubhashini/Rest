package com.cyb.ewallet.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.ewallet.model.TxnType;
@Repository("txnTypeDao")
public class TxnTypeDaoImpl implements TxnTypeDao{

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(TxnTypeDaoImpl.class);
	
	public TxnType findTxnTypeById(String id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		TxnType txnType = (TxnType) session.load(TxnType.class, id);
		logger.info("Txn Type loaded successfully, txnType details="+txnType);
		return txnType;
	}

	public void saveTxnType(TxnType txnType) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(txnType);
		logger.info("txnType saved successfully, txnType Details="+txnType);
	}

	public List<TxnType> findAllTxnTypes() {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TxnType> txnTypeList = (List<TxnType>)session.createQuery("FROM TxnType AS t").list();
		System.out.println("TxnType ....... dao  "+ txnTypeList.size());
		for (TxnType p : txnTypeList) {
			logger.info("TxnType List::" + p);
		}
		return txnTypeList;
	}

	public void deleteTxnTypeById(String id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		TxnType txnType = (TxnType) session.load(TxnType.class, id);
		
		if(null != txnType){
			session.delete(txnType);
		}
		logger.info("TxnType deleted successfully, TxnType details="+txnType);
		
	}

	public boolean isTxnTypeExist(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		TxnType txnType = (TxnType) session.load(TxnType.class, id);
		if(txnType!=null){
			return true;
		}
		else{
			return false;
		}
	}

}
