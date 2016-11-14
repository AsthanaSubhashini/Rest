package com.cyb.ewallet.exception;

public class DataNotFoundException extends Exception {

	    private String errorMessage;
	  

	/**
	 * 
	 */
	private static final long serialVersionUID = 5307377783077688301L;
	
	public DataNotFoundException(String cardNumber)
	{
		super(cardNumber);
		this.errorMessage=cardNumber;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
