package com.cyb.ewallet.facade;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cyb.ewallet.dto.PaymentProcessedDTO;
import com.cyb.ewallet.dto.PaymentTransactionDTO;
import com.cyb.ewallet.model.Customer;
import com.cyb.ewallet.model.Transaction;
import com.cyb.ewallet.model.TxnType;
import com.cyb.ewallet.model.Vendor;
import com.cyb.ewallet.service.CustomerService;
import com.cyb.ewallet.service.PayService;
import com.cyb.ewallet.service.TxnTypeService;
import com.cyb.ewallet.service.VendorService;

@Component("payProcessFacade")
public class PayProcessFacadeImpl implements PayProcessFacade{
	
	@Autowired
	PayService payService;
	
	@Autowired
	VendorService vendorService;
	@Autowired
	CustomerService customerService;
	
	@Autowired
	TxnTypeService txnTypeService;
	
	public PaymentProcessedDTO pay(PaymentTransactionDTO paymentTxnDto,String tokenNumber) {
		// TODO Auto-generated method stub
		if(vendorService.matchVendorByTokenNumber(tokenNumber)){
		PaymentProcessedDTO payData=populatePayProcessedData(paymentTxnDto);
		Customer customer=null;
		if(!StringUtils.isEmpty(paymentTxnDto.getCardNumber()))
			customer=customerService.findCustomerByCardNumber(paymentTxnDto.getCardNumber());
		else if(!StringUtils.isEmpty(paymentTxnDto.getCustomerId())) 
			customer=customerService.findCustomerByEmpId(paymentTxnDto.getCustomerId());
		boolean status=payService.pay(createTransaction(paymentTxnDto,customer),customer.getCardNumber());
		if(status){
			payData.setStatus("0");
			payData.setError_code("");
			payData.setError_desc("");
			return payData;
		}
		else if(!status)
			payData.setStatus("1");
			payData.setError_code("1030");
			payData.setError_desc("Transaction Failure");
		return payData;
		}
		else
			return null;
		
	}
	Transaction createTransaction(PaymentTransactionDTO paymentTxnDto,Customer customer){
		Transaction txn=new Transaction();
	    Vendor vendor=vendorService.findVendorById(paymentTxnDto.getVendorId());
		txn.setCurrencyType(paymentTxnDto.getCurrencyType());
		txn.setTxnId(paymentTxnDto.getTxnId());
		txn.setCustomer(customer);
		txn.setTxnAmount(Double.valueOf(paymentTxnDto.getTxnAmount()));
		txn.setVendor(vendor);
		Timestamp ts = Timestamp.valueOf(paymentTxnDto.getTimeStamp());
		txn.setTimeStamp(ts);
		txn.setOrderId(Long.parseLong(paymentTxnDto.getOrderId()));
		TxnType txnType=txnTypeService.findTxnTypeById(paymentTxnDto.getTxnType());
		txn.setTxnType(txnType);
		return txn;
	}
	PaymentProcessedDTO populatePayProcessedData(PaymentTransactionDTO paymentTxnDto){
		PaymentProcessedDTO paymentProcessedData=new PaymentProcessedDTO();
		paymentProcessedData.setCardNumber(paymentTxnDto.getCardNumber());
		paymentProcessedData.setCurrencyType(paymentTxnDto.getCurrencyType());
		paymentProcessedData.setCustomerId(paymentTxnDto.getCustomerId());
		paymentProcessedData.setOrderId(paymentTxnDto.getOrderId());
		paymentProcessedData.setTimeStamp(paymentTxnDto.getTimeStamp());
		paymentProcessedData.setTxnAmount(paymentTxnDto.getTxnAmount());
		paymentProcessedData.setTxnId(paymentTxnDto.getTxnId());
		paymentProcessedData.setVendorId(paymentTxnDto.getVendorId());
		paymentProcessedData.setTxnType(paymentTxnDto.getTxnType());
		return paymentProcessedData;
	}
}
