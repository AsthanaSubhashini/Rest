package com.cyb.ewallet.dao;

import java.util.List;

import com.cyb.ewallet.model.Vendor;

public interface VendorDao {
	
	Vendor findVendorById(String vendorId);
	
	boolean matchVendorByTokenNumber(String tokenNumber);
	
	void saveVendor(Vendor vendor);
	
	void updateVendor(Vendor vendor);
	
	void deleteVendorById(String vendorId);

	List<Vendor> findAllVendors(); 
	
	void deleteAllVendors();
	
	public boolean isVendorExist(String vendorId);
}
