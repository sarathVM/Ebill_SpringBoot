package com.lpu.ElectricityBillingSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lpu.ElectricityBillingSystem.dao.AdminRepo;
import com.lpu.ElectricityBillingSystem.model.Admin;

public class AdminDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AdminRepo adminrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//fetching user from database
		Admin admin=adminrepo.getAdminByAEmail(username);
		
		if(admin==null) {
			throw new UsernameNotFoundException("Couldnt find user!!") ;
		}
		CustomAdminDetails csad=new CustomAdminDetails(admin);
		
 		
		return csad;
	}

}
