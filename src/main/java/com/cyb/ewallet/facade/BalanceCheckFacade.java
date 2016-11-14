package com.cyb.ewallet.facade;

import com.cyb.ewallet.dto.BalanceEnquiryDTO;


public interface BalanceCheckFacade {
	
	public BalanceEnquiryDTO balanceCheck(String cardNumber);
	

}
