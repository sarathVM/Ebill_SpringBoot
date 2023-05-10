package com.lpu.ElectricityBillingSystem.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lpu.ElectricityBillingSystem.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Integer>{

@Query("from Customer as d where d.admin.aid=:adminId")
public Page<Customer> findCustomersByAdmin(@Param("adminId")int adminId,Pageable pageable);
//Pageable contains info of no. of objects per page AND the current page

}

