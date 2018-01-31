package com.cencosud.middleware.enrollment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.enrollment.model.Enrollment;
import com.cencosud.middleware.enrollment.repository.EnrollmentRepository;
import com.cencosud.middleware.enrollment.service.EnrollmentMongoService;

@Service
public class EnrollmentMongoServiceImpl implements EnrollmentMongoService {

	@Autowired
	private EnrollmentRepository repository;

	@Override
	public Enrollment save(Enrollment enrollment) {
		return repository.save(enrollment);
	}

	@Override
	public List<Enrollment> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Enrollment> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public List<Enrollment> findByRUT(String rut) {
		return repository.findByRUT(rut);
	}

}
