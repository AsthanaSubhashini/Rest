package com.cyb.ewallet.validations;




import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
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

import com.cyb.ewallet.service.CustomerService;

@Target({ METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidCustomer.ValidCustomerValidator.class)
@Documented
public @interface ValidCustomer{

	String message() default "{error.validation.payment_process_error}";
	String error_code() default "1021";
	 Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
	    
	    
	    public static class ValidCustomerValidator implements ConstraintValidator<ValidCustomer, String> {

	    	ValidCustomer validCustomer;
	    	
	    	@Resource(name="customValidator")
	    	CustomValidator customValidator;
	    	@Resource(name="customerService")
	    	CustomerService customerService;
	    	
	    	
			public void initialize(ValidCustomer validCustomer) {
				// TODO Auto-generated method stub
				this.validCustomer=validCustomer;
			}

			public boolean isValid(String cardNumber,
					ConstraintValidatorContext constraintValidatorContext) {
				// TODO Auto-generated method stub
				System.out.println("is valid method of PROPERTY LEVEL TRIGGERED");
				if(StringUtils.isEmpty(cardNumber) || !customerService.isCustomerExist(cardNumber)){
					System.out.println("my validator triggeredd!!!!!!!!!!");
					return true;
				}
				 constraintValidatorContext.disableDefaultConstraintViolation();
		         constraintValidatorContext.buildConstraintViolationWithTemplate(validCustomer.message()+":"+validCustomer.error_code()).addConstraintViolation();
		            return false;
		       
			}
	
    }
}
