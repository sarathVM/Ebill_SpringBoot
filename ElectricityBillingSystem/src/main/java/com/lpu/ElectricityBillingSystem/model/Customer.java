package com.lpu.ElectricityBillingSystem.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cId;
	private String cName;
	private String cPhoneNo;
	private int cUnits;
	private String cAddress;
	private String DOB;
	//private boolean paid=false;
	@ManyToOne()
	private Admin admin;
	
	
	public int getcId() {
		return cId;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(int cId, String cName, String cPhoneNo, int cUnits, String cAddress, String DOB) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cPhoneNo = cPhoneNo;
		this.cUnits = cUnits;
		this.cAddress = cAddress;
		this.DOB = DOB;
		
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcPhoneNo() {
		return cPhoneNo;
	}
	public void setcPhoneNo(String cPhoneNo) {
		this.cPhoneNo = cPhoneNo;
	}
	public int getcUnits() {
		return cUnits;
	}
	public void setcUnits(int cUnits) {
		this.cUnits = cUnits;
	}
	
	public String getcAddress() {
		return cAddress;
	}
	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}
	@Override
	public String toString() {
		return "Customer [cId=" + cId + ", cName=" + cName + ", cPhoneNo=" + cPhoneNo + ", cUnits="
				+ cUnits + ", cAddress=" + cAddress + ", DOB=" + DOB + "]";
	}
	
	
}
