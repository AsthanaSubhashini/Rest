package com.cyb.ewallet.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cyb.ewallet.model.Customer;
import com.cyb.ewallet.model.Vendor;
@Repository("vendorDao")
public class VendorDaoImpl implements VendorDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(VendorDaoImpl.class);

	
	public Vendor findVendorById(String vendorId) {
		Session session = this.sessionFactory.getCurrentSession();
		//@SuppressWarnings("unchecked")
		//Customer customer = ((List<Customer>) session.createQuery("FROM com.cyb.ewallet.model.Customer AS c where c.empId="+id).list()).get(0);
		Vendor vendor = (Vendor) session.load(Vendor.class, vendorId);
		logger.info("customer loaded successfully, customer details="+vendor);
		return vendor;
	}



	public void saveVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(vendor);
		logger.info("vendor saved successfully, vendor Details="+vendor);
	}

	public void updateVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.update(vendor);
	}

	public void deleteVendorById(String vendorId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		Vendor vendor = (Vendor) session.load(Vendor.class, vendorId);
		
		if(null != vendor){
			session.delete(vendor);
		}
		logger.info("vendor deleted successfully, vendor details="+vendor);
	}

	public List<Vendor> findAllVendors() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Vendor> vendorList = (List<Vendor>)session.createQuery("FROM Vendor AS v").list();
		System.out.println("checkbal ....... dao  "+ vendorList.size());
		for (Vendor p : vendorList) {
			System.out.println("Person List::" + p);
		}
		return vendorList;
	}

	public void deleteAllVendors() {
		// TODO Auto-generated method stub
		for(Vendor customer:findAllVendors()){
			deleteVendorById(customer.getVendorId());
		}
	}

	public boolean isVendorExist(String vendorId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		//@SuppressWarnings("unchecked")
		//Customer customer = ((List<Customer>) session.createQuery("FROM com.cyb.ewallet.model.Customer AS c where c.empId="+id).list()).get(0);
		Vendor vendor1 = (Vendor) session.load(Vendor.class, vendorId);
		if(vendor1!=null){
			return true;
		}
		else{
			return false;
		}
	}



	public boolean matchVendorByTokenNumber(String tokenNumber) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Vendor> vendorList = (List<Vendor>)session.createQuery("FROM Vendor AS v where v.tokenNumber='"+tokenNumber+"'").list();
		System.out.println("checkbal ....... dao  "+ vendorList.size());
		if(!vendorList.isEmpty())
			return true;
		else
			return false;
	}

}
