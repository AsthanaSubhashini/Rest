package com.cyb.ewallet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.ewallet.model.Customer;
import com.mysql.jdbc.log.Log;

@Repository("customerDao")
@Transactional
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);



	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(customer);
	}

	public Customer findCustomerByCardNumber(String cardNumber) {
		Session session = this.sessionFactory.getCurrentSession();		
		Customer customer = (Customer) session.load(Customer.class, cardNumber);
		logger.info("customer loaded successfully, customer details="+customer);
		return customer;
	}

	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(customer);
		logger.info("customer saved successfully, customer Details="+customer);
	}

	public void deleteCustomerById(long id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.load(Customer.class, id);
		if(null != customer){
			session.delete(customer);
		}
		logger.info("customer deleted successfully, person details="+customer);
	}

	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Customer> customerList = (List<Customer>)session.createQuery("FROM com.cyb.ewallet.model.Customer AS c").list();
		System.out.println("checkbal ....... dao  "+ customerList.size());
		for (Customer p : customerList) {
			System.out.println("Person List::" + p);
		}
		return customerList;
	}

	public void deleteAllCustomers() {
		// TODO Auto-generated method stub
		for(Customer customer:findAllCustomers()){
			deleteCustomerById(customer.getEmpId());
		}
	}

	public boolean isCustomerExist(String cardNumber) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Customer cust = (Customer) session.get(Customer.class, cardNumber);
		if(cust!=null){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isCustomerIdValid(Long empId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Customer> customerList = (List<Customer>)session.createQuery("FROM com.cyb.ewallet.model.Customer AS c where c.empId="+empId).list();
		if(customerList.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}

	public Customer findCustomerByEmpId(String empId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Customer> customerList = (List<Customer>)session.createQuery("FROM com.cyb.ewallet.model.Customer AS c where c.empId="+empId).list();
		if(!customerList.isEmpty())
			return customerList.get(0);
		else 
			return null;
	}
}
