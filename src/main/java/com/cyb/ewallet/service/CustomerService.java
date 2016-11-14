package com.cyb.ewallet.service;

import java.util.List;

import com.cyb.ewallet.model.Customer;

public interface CustomerService {

	
	Customer findCustomerByCardNumber(String cardNumber);
	
	void saveCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomerById(long id);

	List<Customer> findAllCustomers(); 
	
	void deleteAllCustomers();
	
	public boolean isCustomerExist(String cardNumber);
	boolean isCustomerIdValid(Long empId);
	Customer findCustomerByEmpId(String empId);

}
