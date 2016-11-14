package com.cyb.ewallet.service;

import com.cyb.ewallet.model.Transaction;

public interface PayService {
	boolean pay(Transaction txn,String cardNumber);
}
