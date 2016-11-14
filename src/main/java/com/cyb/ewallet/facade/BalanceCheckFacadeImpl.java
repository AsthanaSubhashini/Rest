package com.cyb.ewallet.facade;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyb.ewallet.dto.BalanceEnquiryDTO;
import com.cyb.ewallet.model.Customer;
import com.cyb.ewallet.service.CustomerService;

@Component("balanceCheckService")
public class BalanceCheckFacadeImpl implements BalanceCheckFacade {
	
	@Autowired
	CustomerService customerService;

	public BalanceEnquiryDTO balanceCheck(String cardNumber) {
		
		
		BalanceEnquiryDTO balanceCheckData =  populateBalanceCheckData(cardNumber);
		
		return balanceCheckData;
		
		// TODO Auto-generated method stub
		
	}
	
	
	BalanceEnquiryDTO populateBalanceCheckData(String cardNumber){
		BalanceEnquiryDTO balanceEnquiryDTO=new BalanceEnquiryDTO();
		Customer customer=customerService.findCustomerByCardNumber(cardNumber);
				balanceEnquiryDTO.setAvailableBalance(customer.getbBalance().toString());
				balanceEnquiryDTO.setCardNumber(customer.getCardNumber());
				balanceEnquiryDTO.setCustomerId(customer.getEmpId().toString());
				Timestamp ts=new Timestamp((new Date()).getTime());
				balanceEnquiryDTO.setTimeStamp(ts.toString());
				balanceEnquiryDTO.setStatus("0");
				balanceEnquiryDTO.setError_code("");
				balanceEnquiryDTO.setError_desc("");
				return balanceEnquiryDTO;
		
	}

}
