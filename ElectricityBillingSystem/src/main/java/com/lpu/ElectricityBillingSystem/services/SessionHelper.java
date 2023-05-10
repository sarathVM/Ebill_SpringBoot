package com.lpu.ElectricityBillingSystem.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionHelper {
	
	public void removeMessage() {
		try {
			HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
			session.removeAttribute("message");
		} catch (Exception e) {
			
			e.printStackTrace();

		}
	}
	

}
