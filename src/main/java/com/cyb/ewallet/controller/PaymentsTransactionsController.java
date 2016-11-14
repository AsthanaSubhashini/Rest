package com.cyb.ewallet.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.ewallet.dto.PaymentProcessedDTO;
import com.cyb.ewallet.dto.PaymentTransactionDTO;
import com.cyb.ewallet.facade.PayProcessFacade;

@RestController
public class PaymentsTransactionsController {
	
	@Autowired
	PayProcessFacade payProcessFacade;
	
	
	
	@RequestMapping( value= "/pay",method=RequestMethod.POST)
	public ResponseEntity<PaymentProcessedDTO> pay( @Valid @RequestBody PaymentTransactionDTO paymentTxnDto,HttpServletRequest request,BindingResult result){
		System.out.println("------customer   : "+paymentTxnDto.getTimeStamp());
		// return HTTP response 200 in case of success
		//customValidator.validate(paymentTxnDto, result);
		/*if(result.hasErrors()){
			try {
				throw new DataNotFoundException(paymentTxnDto.getCardNumber());
			} catch (DataNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		String tokenNumber=request.getHeader("tokenNumber");
		System.out.println("token number: "+tokenNumber);
		PaymentProcessedDTO payData= payProcessFacade.pay(paymentTxnDto,tokenNumber);
		if(payData!=null)
			return new ResponseEntity<PaymentProcessedDTO>(payData, HttpStatus.OK);
		else
			return new ResponseEntity<PaymentProcessedDTO>(payData, HttpStatus.NOT_FOUND);
	}
	
}
