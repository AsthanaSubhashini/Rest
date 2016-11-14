package com.cyb.ewallet.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyb.ewallet.dao.CustomerDao;
import com.cyb.ewallet.exception.DataNotFoundException;
import com.cyb.ewallet.model.Customer;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Resource(name = "customerDao")
	CustomerDao customerDao;


	@Transactional
	public Customer findCustomerByCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		
		return customerDao.findCustomerByCardNumber(cardNumber);
	}
	@Transactional
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.saveCustomer(customer);
	}
	@Transactional
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.updateCustomer(customer);
	}
	@Transactional
	public void deleteCustomerById(long id) {
		// TODO Auto-generated method stub
		customerDao.deleteCustomerById(id);
	}
	@Transactional
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return customerDao.findAllCustomers();
	}
	@Transactional
	public void deleteAllCustomers() {
		// TODO Auto-generated method stub
		customerDao.deleteAllCustomers();
	}
	@Transactional
	public boolean isCustomerExist(String cardNumber) {
		// TODO Auto-generated method stub
		return customerDao.isCustomerExist(cardNumber);
	}
	@Transactional
	public boolean isCustomerIdValid(Long empId) {
		// TODO Auto-generated method stub
		return customerDao.isCustomerIdValid(empId);
	}
	public Customer findCustomerByEmpId(String empId) {
		// TODO Auto-generated method stub
		return customerDao.findCustomerByEmpId(empId);
	}

}
