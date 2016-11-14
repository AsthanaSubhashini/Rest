package com.cyb.ewallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyb.ewallet.model.Customer;
import com.cyb.ewallet.model.Transaction;

@Component("payService")
public class PayServiceImpl implements PayService {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	TransactionService transactionService;

	public boolean pay(Transaction txn, String cardNumber) {
		// TODO Auto-generated method stub
		Customer customer=customerService.findCustomerByCardNumber(cardNumber);
		if(customer.getbBalance()>txn.getTxnAmount()){
			customer.setbBalance(customer.getbBalance()-txn.getTxnAmount());
			txn.setStatus(true);
			customer.setLastTxnTimeStamp(txn.getTimeStamp());
			customerService.updateCustomer(customer);
			transactionService.saveTransaction(txn);
			return true;
		}
		else
			return false;
	}
}
