package com.cyb.ewallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cyb.ewallet.dto.BalanceEnquiryDTO;
import com.cyb.ewallet.dto.PaymentProcessedDTO;
import com.cyb.ewallet.dto.PaymentTransactionDTO;
import com.cyb.ewallet.exception.DataNotFoundException;
import com.cyb.ewallet.validations.CustomValidator;

@ControllerAdvice
public class WalletErrorControllerHandler {

	@Autowired
	CustomValidator customValidator;
	
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<BalanceEnquiryDTO> exceptionHandler(Exception ex) {
		String cardNumber=ex.getMessage();
		BalanceEnquiryDTO dto=customValidator.addErrorFields(cardNumber);
		return new ResponseEntity<BalanceEnquiryDTO>(dto, HttpStatus.OK);

	}
	
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ResponseBody
	    public PaymentProcessedDTO processValidationError(MethodArgumentNotValidException ex) {
	        BindingResult result = ex.getBindingResult();
	        ObjectError fieldErrors = result.getAllErrors().get(0);
	        PaymentTransactionDTO target=(PaymentTransactionDTO)ex.getBindingResult().getTarget();
	        PaymentProcessedDTO dto=processFieldErrors(fieldErrors,target);
	        return dto;
	    }
	 
	    private PaymentProcessedDTO processFieldErrors(ObjectError fieldErrors,PaymentTransactionDTO target) {
	        PaymentProcessedDTO dto = new PaymentProcessedDTO();
	        dto.setCardNumber(target.getCardNumber());
	        dto.setCurrencyType(target.getCurrencyType());
	        dto.setCustomerId(target.getCustomerId());
	        dto.setOrderId(target.getOrderId());
	        dto.setStatus("1");
	        dto.setTimeStamp(target.getTimeStamp());
	        dto.setTxnAmount(target.getTxnAmount());
	        dto.setTxnId(target.getTxnId());
	        dto.setVendorId(target.getVendorId());
	        String errors=fieldErrors.getDefaultMessage();
	        String [] errorArr=errors.split("\\|");
	        System.out.println("arr lenght:  "+errorArr.length);
	        dto.setError_desc(errorArr[1]);
	        dto.setError_code(errorArr[0]);
	        return dto;
	    }
	 

}
