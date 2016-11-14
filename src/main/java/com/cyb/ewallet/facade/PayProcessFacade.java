package com.cyb.ewallet.facade;

import com.cyb.ewallet.dto.PaymentProcessedDTO;
import com.cyb.ewallet.dto.PaymentTransactionDTO;

public interface PayProcessFacade {
	PaymentProcessedDTO pay(PaymentTransactionDTO payTxnDto,String tokenNumber);
}
