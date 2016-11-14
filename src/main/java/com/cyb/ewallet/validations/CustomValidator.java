package com.cyb.ewallet.validations;
//import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cyb.ewallet.dto.BalanceEnquiryDTO;
import com.cyb.ewallet.dto.PaymentProcessedDTO;
import com.cyb.ewallet.dto.PaymentTransactionDTO;
import com.cyb.ewallet.model.Customer;
import com.cyb.ewallet.service.CustomerService;

@Component("customValidator")
public class CustomValidator implements Validator {
	
	
	
	@Autowired
	private CustomerService customerService;
	private static final Logger logger = LoggerFactory.getLogger(CustomValidator.class);
	private static final String ACTIVE_DAY="active";
	private static final String INACTIVE_DAY="inactive";

	
	public boolean IsCustomerValid(PaymentTransactionDTO paymentTransactionDTO)
	{
		//Customer customer=customerService.findCustomerByCardNumber(paymentTransactionDTO.getCardNumber());
		if(customerService.isCustomerExist(paymentTransactionDTO.getCardNumber()))
		{
			return true;
		}
			return false;
	}
	
	
	
	
	public String getActiveDay(String activeDays){
		final String[] activeDaysArr = activeDays.split("|", 0);
		Date dt=new Date();
		@SuppressWarnings("deprecation")
		String todayActiveDay=activeDaysArr[dt.getDay()*2];
		if(todayActiveDay.equals("1")){
			return ACTIVE_DAY;
		}
		else{
			return INACTIVE_DAY;
		}
	}
	
	public boolean isActiveDay(String activeDays){
		String activeDay=getActiveDay(activeDays);
		if(activeDay.equals(ACTIVE_DAY))
			return true;
		else
			return false;
	}
	
	 public boolean validCardNumber(PaymentTransactionDTO paymentTransactionDTO, Customer customer)
	 {
		
		 if(paymentTransactionDTO.getCardNumber().equals(customer.getCardNumber()))
		 {
			 return true;
		 }
		 
		 else{
				return false;
			}
		
		 
		 
	 }
	 
	  public BalanceEnquiryDTO addErrorFields(String cardNumber){
			BalanceEnquiryDTO balanceEnquiryDTO=new BalanceEnquiryDTO();
			balanceEnquiryDTO.setCardNumber(cardNumber);
			balanceEnquiryDTO.setAvailableBalance("0.0");
			balanceEnquiryDTO.setTimeStamp((new Timestamp(new Date().getTime()).toString()));
			balanceEnquiryDTO.setError_code(new Integer(HttpStatus.BAD_REQUEST.value()).toString());
			balanceEnquiryDTO.setError_desc("invalid user id");
			balanceEnquiryDTO.setStatus("1");
			return balanceEnquiryDTO;
		}
	/* 
	 public boolean checkBalance(Customer empId, PaymentTransactionDTO paymentTransactionDTO)
	 {
		 if(paymentTransactionDTO.getCustomerId().equals(empId))
		 {
			 return true;
		 }
		 else{
				return false;
			}
		 
	 }*/




	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return new PaymentTransactionDTO().equals(paramClass);
	}




	public void validate(Object target, Errors e) {
		// TODO Auto-generated method stub
		logger.info("CUSTOM VALIDATIONS!!!");
		PaymentTransactionDTO paymentTransactionDTO=(PaymentTransactionDTO)target;
		if(!IsCustomerValid(paymentTransactionDTO)){
			e.reject("cardNumber", "Customer with card number " +paymentTransactionDTO.getCardNumber()+ " does not exsists");
		}
	}

	 
	
	
	
}
