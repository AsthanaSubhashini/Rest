package com.cyb.ewallet.dao;

import java.util.List;

import com.cyb.ewallet.model.TxnType;



public interface TxnTypeDao {
	TxnType findTxnTypeById(String id);
	void saveTxnType(TxnType txnType);
	List<TxnType> findAllTxnTypes(); 
	void deleteTxnTypeById(String id);
	public boolean isTxnTypeExist(String id);
}
