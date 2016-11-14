package com.cyb.ewallet.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyb.ewallet.dao.VendorDao;
import com.cyb.ewallet.model.Vendor;

@Service("vendorService")
@Transactional
public class VendorServiceImpl implements VendorService {
	@Autowired
	VendorDao vendorDao;
	
	public Vendor findVendorById(String vendorId) {
		// TODO Auto-generated method stub
		return vendorDao.findVendorById(vendorId);
	}



	public void saveVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		vendorDao.saveVendor(vendor);
	}

	public void updateVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		vendorDao.updateVendor(vendor);
	}

	public void deleteVendorById(String vendorId) {
		// TODO Auto-generated method stub
		vendorDao.deleteVendorById(vendorId);
	}

	public List<Vendor> findAllVendors() {
		// TODO Auto-generated method stub
		return vendorDao.findAllVendors();
	}

	public void deleteAllVendors() {
		// TODO Auto-generated method stub
		vendorDao.deleteAllVendors();
	}

	public boolean isVendorExist(String vendorId){
		// TODO Auto-generated method stub
		return vendorDao.isVendorExist(vendorId);
	}



	public boolean matchVendorByTokenNumber(String tokenNumber) {
		// TODO Auto-generated method stub
		return vendorDao.matchVendorByTokenNumber(tokenNumber);
	}

}
