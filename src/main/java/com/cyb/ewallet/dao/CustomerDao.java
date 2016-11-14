package com.cyb.ewallet.dao;

import java.util.List;

import com.cyb.ewallet.model.Customer;

public interface CustomerDao {

	Customer findCustomerByCardNumber(String cardNumber);
	
	Customer findCustomerByEmpId(String empId);
	
	void saveCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomerById(long id);

	List<Customer> findAllCustomers(); 
	
	void deleteAllCustomers();
	
	boolean isCustomerIdValid(Long empId);
	
	public boolean isCustomerExist(String cardNumber);

}