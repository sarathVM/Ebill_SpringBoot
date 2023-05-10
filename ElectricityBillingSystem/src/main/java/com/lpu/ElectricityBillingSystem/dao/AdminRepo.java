package com.lpu.ElectricityBillingSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lpu.ElectricityBillingSystem.model.Admin;

public interface AdminRepo  extends CrudRepository<Admin,Integer> {
	@Query("select u from Admin u where u.aEmail= :email")
	public Admin getAdminByAEmail(@Param("email") String email);
}
