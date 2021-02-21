package com.oopclass.breadapp.repository;

import com.oopclass.breadapp.models.Customer;
import com.oopclass.breadapp.models.Service1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oopclass.breadapp.models.User;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Repository
public interface Service1Repository extends JpaRepository<Service1, Long> {

	//User findByEmail(String email);
}
