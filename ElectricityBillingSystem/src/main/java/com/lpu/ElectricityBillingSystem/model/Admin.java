package com.lpu.ElectricityBillingSystem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int aid;
	private String aName;
	@javax.persistence.Column(unique = true)
	private String aEmail;
	private String aPass;
	@javax.persistence.Column(unique = true)
	private String state;
	private int unitcost;
	private String Role;
	
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	@javax.persistence.OneToMany(cascade = CascadeType.ALL,fetch=javax.persistence.FetchType.LAZY,mappedBy = "admin" )
	private List<Customer> customers=new ArrayList<>();
	
	public Admin(int aid, String aName, String aEmail, String aPass, String state, int unitcost, String role,
			List<Customer> customers) {
		super();
		this.aid = aid;
		this.aName = aName;
		this.aEmail = aEmail;
		this.aPass = aPass;
		this.state = state;
		this.unitcost = unitcost;
		Role = role;
		this.customers = customers;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public String getaEmail() {
		return aEmail;
	}
	public void setaEmail(String aEmail) {
		this.aEmail = aEmail;
	}
	public String getaPass() {
		return aPass;
	}
	public void setaPass(String aPass) {
		this.aPass = aPass;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getUnitcost() {
		return unitcost;
	}
	public void setUnitcost(int unitcost) {
		this.unitcost = unitcost;
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", aName=" + aName + ", aEmail=" + aEmail + ", aPass=" + aPass + ", state=" + state
				+ ", unitcost=" + unitcost + ", customers=" + customers + "]";
	}
	
	
}
