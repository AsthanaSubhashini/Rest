package com.cyb.ewallet.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cyb.ewallet.dao.TxnTypeDao;
import com.cyb.ewallet.model.TxnType;
@Service("txnTypeService")
@Transactional
public class TxnTypeServiceImpl implements TxnTypeService {

	@Resource(name="txnTypeDao")
	TxnTypeDao txnTypeDao;
	
	public TxnType findTxnTypeById(String id) {
		// TODO Auto-generated method stub
		return txnTypeDao.findTxnTypeById(id);
	}

	public void saveTxnType(TxnType txnType) {
		// TODO Auto-generated method stub
		txnTypeDao.saveTxnType(txnType);
	}

	public List<TxnType> findAllTxnTypes() {
		// TODO Auto-generated method stub
		return txnTypeDao.findAllTxnTypes();
	}

	public void deleteTxnTypeById(String id) {
		// TODO Auto-generated method stub
		txnTypeDao.deleteTxnTypeById(id);
	}

	public boolean isTxnTypeExist(String id) {
		// TODO Auto-generated method stub
		return txnTypeDao.isTxnTypeExist(id);
	}

}
