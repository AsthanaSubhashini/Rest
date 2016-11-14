package com.cyb.ewallet.validations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.annotation.Resource;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.util.StringUtils;

import com.cyb.ewallet.dto.PaymentTransactionDTO;
import com.cyb.ewallet.model.Customer;
import com.cyb.ewallet.service.CustomerService;
import com.cyb.ewallet.service.VendorService;


@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckTransaction.CheckTransactionAmountValidator.class)
@Documented
public @interface CheckTransaction {
	String message() default "{error.validation.payment_process_error}";
	 Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
	    
	    
	    public static class CheckTransactionAmountValidator implements ConstraintValidator<CheckTransaction, PaymentTransactionDTO> {

	    	CheckTransaction checkTransactionAmount;
	    	private String error_message;
	    	
	    	@Resource(name="customerService")
	    	CustomerService customerService;
	    	
	    	@Resource(name="vendorService")
	    	VendorService vendorService;
	    	
	    	@Resource(name="customValidator")
	    	CustomValidator customValidator;
	    	
	    	Customer customer;
	    
			public void initialize(CheckTransaction checkTransactionAmount) {
				// TODO Auto-generated method stub
				this.checkTransactionAmount=checkTransactionAmount;
			}
			public boolean isValid(PaymentTransactionDTO paymentTransactionDTO,
					ConstraintValidatorContext constraintValidatorContext) {
				// TODO Auto-generated method stub
				System.out.println("is valid method of annot validation triggerd");
				boolean cardAndEmpIdCheck=(StringUtils.isEmpty(paymentTransactionDTO.getCardNumber())&&(StringUtils.isEmpty(paymentTransactionDTO.getCustomerId())));
				boolean paymentTransactionDtoIsEmpty=(StringUtils.isEmpty(paymentTransactionDTO.getCurrencyType())||StringUtils.isEmpty(paymentTransactionDTO.getOrderId())||StringUtils.isEmpty(paymentTransactionDTO.getTimeStamp())||StringUtils.isEmpty(paymentTransactionDTO.getTxnAmount())||StringUtils.isEmpty(paymentTransactionDTO.getTxnId())||StringUtils.isEmpty(paymentTransactionDTO.getVendorId()));
				if(cardAndEmpIdCheck){
					constraintValidatorContext.disableDefaultConstraintViolation();
					setError_message("1012|CardNumber and CustomerId cannot be empty, Atleast one is required!!");
			        constraintValidatorContext.buildConstraintViolationWithTemplate(getError_message()).addConstraintViolation();
			        return false;
				}
				else if(paymentTransactionDtoIsEmpty){
					constraintValidatorContext.disableDefaultConstraintViolation();
					setError_message("1011|fields should not be empty !!!!");
			        constraintValidatorContext.buildConstraintViolationWithTemplate(getError_message()).addConstraintViolation();
			        return false;
				}
				else if(!vendorService.isVendorExist(paymentTransactionDTO.getVendorId())){
					constraintValidatorContext.disableDefaultConstraintViolation();
					setError_message("1001|VendorId:"+ paymentTransactionDTO.getVendorId()+ " does not exsists!!!!");
			        constraintValidatorContext.buildConstraintViolationWithTemplate(getError_message()).addConstraintViolation();
			        return false;
				}
				
				else if((!paymentTransactionDTO.getCardNumber().matches("^(\\s*|\\d+)$")) || (!paymentTransactionDTO.getCustomerId().matches("^(\\s*|\\d+)$"))){
					constraintValidatorContext.disableDefaultConstraintViolation();
					setError_message("1002|CardNumber, CustomerId should contains only Numbers");
			        constraintValidatorContext.buildConstraintViolationWithTemplate(getError_message()).addConstraintViolation();
			        return false;
				}
				
				else if(!(paymentTransactionDTO.getOrderId().matches("[0-9]+")) || !(paymentTransactionDTO.getTxnId().matches("[0-9]+"))){
					constraintValidatorContext.disableDefaultConstraintViolation();
					setError_message("1003| OrderId ,transactionId should contains only Numbers");
			        constraintValidatorContext.buildConstraintViolationWithTemplate(getError_message()).addConstraintViolation();
			        return false;
				}
				
				
				else if((!customerService.isCustomerExist(paymentTransactionDTO.getCardNumber())) && (!StringUtils.isEmpty(paymentTransactionDTO.getCardNumber())))
				{
					constraintValidatorContext.disableDefaultConstraintViolation();
					setError_message("1004|Cardnumber:"+paymentTransactionDTO.getCardNumber()+" does not exsists!!!!");
			        constraintValidatorContext.buildConstraintViolationWithTemplate(getError_message()).addConstraintViolation();
			        return false;
		        }
				
				else if((!StringUtils.isEmpty(paymentTransactionDTO.getCustomerId()))&&(!customerService.isCustomerIdValid(Long.parseLong(paymentTransactionDTO.getCustomerId()))))
				{
					constraintValidatorContext.disableDefaultConstraintViolation();
					setError_message("1005|customer with CustomerId:"+paymentTransactionDTO.getCustomerId()+" does not exsists!!!!");
			        constraintValidatorContext.buildConstraintViolationWithTemplate(getError_message()).addConstraintViolation();
			        return false;
		        }
				
				if(StringUtils.isEmpty(paymentTransactionDTO.getCardNumber())){
						customer=customerService.findCustomerByEmpId(paymentTransactionDTO.getCustomerId());
				}
				else if(StringUtils.isEmpty(paymentTransactionDTO.getCustomerId())){
					customer=customerService.findCustomerByCardNumber(paymentTransactionDTO.getCardNumber());
				}
			   if(Double.parseDouble(paymentTransactionDTO.getTxnAmount())>customer.getbBalance())
				{
					constraintValidatorContext.disableDefaultConstraintViolation();
					setError_message("1006|Insufficient Balance in account for transaction, Available Balance: "+customer.getbBalance());
			        constraintValidatorContext.buildConstraintViolationWithTemplate(getError_message()).addConstraintViolation();
			        return false;
		        }
				
				else if(!customValidator.isActiveDay(customer.getActiveDays())){
					constraintValidatorContext.disableDefaultConstraintViolation();
					setError_message("1007|Inactive day, Sorry you cannot complete transaction");
			        constraintValidatorContext.buildConstraintViolationWithTemplate(getError_message()).addConstraintViolation();
			        return false;
				}
				
				
				return true;
			}
			public String getError_message() {
				return error_message;
			}
			public void setError_message(String error_message) {
				this.error_message = error_message;
			}
		
   }
}
