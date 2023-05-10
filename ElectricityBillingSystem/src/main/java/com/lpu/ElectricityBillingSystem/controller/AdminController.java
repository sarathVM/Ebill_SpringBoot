package com.lpu.ElectricityBillingSystem.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpu.ElectricityBillingSystem.dao.AdminRepo;
import com.lpu.ElectricityBillingSystem.dao.CustomerRepo;
import com.lpu.ElectricityBillingSystem.model.Admin;
import com.lpu.ElectricityBillingSystem.model.Customer;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private CustomerRepo cs;
	@ModelAttribute
	public void addCommonData(Model m,Principal p) {
		String adminEmail=p.getName();
		Admin admin=adminRepo.getAdminByAEmail(adminEmail);
		m.addAttribute(admin);
	}
	
	//*****index OR home
	@RequestMapping("/index")
	public String indexPage(Model m)
	{	
		m.addAttribute("tittle","Index Page");
		return "admin/index";
	}
//*****open add customer form
	@GetMapping("/add")
	public String openAddContactForm(Model m) {
		m.addAttribute("tittle","Add Customer");
		m.addAttribute("customer",new Customer());
		
		return "admin/addCustomer";
	}
	@PostMapping("/process")
	public String process1(@ModelAttribute("customer") Customer customer,Principal p) {
		//System.out.println(customer);
		String name=p.getName();
		Admin admin=adminRepo.getAdminByAEmail(name);
		customer.setAdmin(admin);
		admin.getCustomers().add(customer);
		
		adminRepo.save(admin);
		
		return "redirect:/admin/view/0";
		 
	}
	@GetMapping("/view/{page}")
	public String viewCust(@PathVariable("page") Integer page,Model m,Principal p) {
		m.addAttribute("tittle","Customer Details Page");
		String adminEmail=p.getName();
		Admin admin=adminRepo.getAdminByAEmail(adminEmail);
		Pageable pageable =PageRequest.of(page,5);
		Page<Customer> customers=cs.findCustomersByAdmin(admin.getAid(),pageable);
		m.addAttribute("customers",customers);
		m.addAttribute("currentPage","page");
		m.addAttribute("totalPages",customers.getTotalPages());
		m.addAttribute(admin);
		return "admin/view";
	}
	@GetMapping("/delete/{cId}")
	public String delete(@PathVariable("cId") Integer cid,Model m,Principal p) {
		String adminEmail=p.getName();
		Admin admin=adminRepo.getAdminByAEmail(adminEmail); 
		Optional<Customer> customerOptional=cs.findById(cid);
		Customer customer=customerOptional.get();
		 if(customer.getAdmin().getAid()==admin.getAid()) {
			 customer.setAdmin(null);
			 cs.delete(customer);
		 }
		//cs.delete(customer);
		return "redirect:/admin/view/0";
	}
	
	
	//open update form handler
	@PostMapping("/update/{cId}")
	public String update(@PathVariable("cId")Integer cid,Model m) {
		m.addAttribute("tittle","Update Page");
		Customer customer=cs.findById(cid).get();
		m.addAttribute("customer",customer);
		return "admin/update";
		
	}
	
	//update proccess
	@PostMapping("/updateProcess")
	public String updateProcess(@ModelAttribute("customer") Customer customer,Model m,Principal p ) {
		try {
			String adminEmail=p.getName();
			Admin admin=adminRepo.getAdminByAEmail(adminEmail); 
			customer.setAdmin(admin);
			cs.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return"redirect:/admin/view/0";
		
	}
	

}
