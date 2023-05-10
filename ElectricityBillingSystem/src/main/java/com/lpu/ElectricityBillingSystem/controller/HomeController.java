package com.lpu.ElectricityBillingSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lpu.ElectricityBillingSystem.dao.AdminRepo;
import com.lpu.ElectricityBillingSystem.dao.CustomerRepo;
import com.lpu.ElectricityBillingSystem.message.Message;
import com.lpu.ElectricityBillingSystem.model.Admin;
import com.lpu.ElectricityBillingSystem.model.Customer;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private CustomerRepo cs;
	@Autowired
	private AdminRepo ad;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@RequestMapping("/")
	public String home(Model m) {
		m.addAttribute("tittle","Electricity Billng System"); 
		return "home";
		
	}
	@RequestMapping("/about")
	public String about(Model m) {
		m.addAttribute("tittle","About page"); 
		return "about";
		
	}
	@RequestMapping("/view")
	public String view(@ModelAttribute("customer") Customer customer) {
	List<Customer> customers=(List<Customer>) cs.findAll();
    System.out.println(customers);
		return "home";
}
	@RequestMapping("/signup")
	public String signup(Model m) {
		m.addAttribute("tittle","New admin signup");
		m.addAttribute("admin",new Admin());
		return "signup";
		
	}
	//handler for admin signup form
	@PostMapping("/do_Register")
	public String registerAdmin(@ModelAttribute("admin") Admin admin,@RequestParam(value="agreement" ,defaultValue = "false") boolean agreement,Model m,HttpSession session ) {
		System.out.println(admin);
		try {
			if(!agreement) {
				throw new Exception("You have not agreed to the terms and conditins");
			}
			System.out.println(admin);
			m.addAttribute("admin",new Admin());
			admin.setRole("ROLE_ADMIN");
			admin.setaPass(passwordEncoder.encode(admin.getaPass()));
			Admin result=ad.save(admin);
			session.setAttribute("message",new Message("Successfully added a new admin","alert-success"));
			return "signup";
		} 
		catch(DataIntegrityViolationException e) {
			m.addAttribute("admin",admin);
			session.setAttribute("message",new Message("Something went wrong!! Try entering different emailId or State ","alert-danger"));
			return "signup";
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("admin",admin);
			session.setAttribute("message",new Message("Something Went Wrong!!"+e.getMessage(),"alert-danger"));
			return "signup";
			
		}
	}
	//handler forr custom login
	@GetMapping("/signin")
	public String login(Model m) {
		m.addAttribute("tittle","Login Page");
		return "login";
	}
	@RequestMapping("/index")
	public String indexPage()
	{	
		return "index";
	}

	
	@RequestMapping("/searchID")
	public String search(@RequestParam(value="search") Integer cId,Model m,HttpSession session)
	{	try {
		Customer customer=cs.findById(cId).get();
		m.addAttribute(customer);
		Admin admin=customer.getAdmin();
		m.addAttribute(admin);
		
		return "billing";
	} catch (Exception e) {
		session.setAttribute("message",new Message("Could'nt find customer!! Enter correct Customer Id","alert-danger"));
		return "home";
	}
	
	}
	
}
