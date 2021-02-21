package com.oopclass.breadapp.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oopclass.breadapp.models.Service1;
import com.oopclass.breadapp.repository.Service1Repository;
import com.oopclass.breadapp.services.IService1Service;

/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Service
public class Service1Service implements IService1Service {
	
	@Autowired
	private Service1Repository service1Repository;
	
	@Override
	public Service1 save(Service1 entity) {
		return service1Repository.save(entity);
	}

	@Override
	public Service1 update(Service1 entity) {
		return service1Repository.save(entity);
	}

	@Override
	public void delete(Service1 entity) {
		service1Repository.delete(entity);
	}

	@Override
	public void delete(Long id) {
		service1Repository.deleteById(id);
	}

	@Override
	public Service1 find(Long id) {
		return service1Repository.findById(id).orElse(null);
	}

	@Override
	public List<Service1> findAll() {
		return service1Repository.findAll();
	}

	@Override
	public void deleteInBatch(List<Service1> service1s) {
		service1Repository.deleteInBatch(service1s);
	}
	
}
