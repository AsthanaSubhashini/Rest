package com.cyb.ewallet.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyb.ewallet.dto.BalanceEnquiryDTO;
import com.cyb.ewallet.exception.DataNotFoundException;
import com.cyb.ewallet.facade.BalanceCheckFacade;
//import com.cyb.ewallet.model.ErrorMessage;
import com.cyb.ewallet.service.CustomerService;
import com.cyb.ewallet.service.TransactionService;
import com.cyb.ewallet.validations.CustomValidator;

@RestController
public class BalanceEnquiryController {

	@Resource(name="customerService")
	CustomerService customerService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	BalanceCheckFacade balanceCheckFacade;

	/*
	 * @Autowired CustomValidator customValidator;
	 */

	@RequestMapping(value = "/balanceCheck/{cardNumber}")
	public ResponseEntity<BalanceEnquiryDTO> balanceCheckProcess(@PathVariable("cardNumber") String cardNumber)
			throws DataNotFoundException {

		CustomValidator customValidator = new CustomValidator();
		
		

		System.out.println("------customer   : " + cardNumber);
		if (!customerService.isCustomerExist(cardNumber)) {
			System.out.println("sdfsdfsdfsdfsfsdfsfsdf");
		
			throw new DataNotFoundException(cardNumber);
		
			//ResponseEntity<BalanceEnquiryDTO>(customValidator.addErrorFields(cardNumber),
			// HttpStatus.BAD_REQUEST);
		}

		BalanceEnquiryDTO balanceEnquiryDTO = balanceCheckFacade.balanceCheck(cardNumber);

		return new ResponseEntity<BalanceEnquiryDTO>(balanceEnquiryDTO, HttpStatus.OK);
	}

}

/*
 * @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Data Not Found") // 404
 * class DataNotFoundException extends RuntimeException {
 * 
 * 
 * 
 * }
 */
