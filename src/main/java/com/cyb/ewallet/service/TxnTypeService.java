package com.cyb.ewallet.service;

import java.util.List;

import com.cyb.ewallet.model.TxnType;

public interface TxnTypeService {
	TxnType findTxnTypeById(String id);
	void saveTxnType(TxnType txnType);
	List<TxnType> findAllTxnTypes(); 
	void deleteTxnTypeById(String id);
	public boolean isTxnTypeExist(String id);
}
