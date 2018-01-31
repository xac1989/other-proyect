package com.cencosud.middleware.enrollment.service;

import java.util.List;

import com.cencosud.middleware.enrollment.model.Enrollment;

public interface EnrollmentMongoService {
	
	/**
	 * Crea un enrollment.
	 * @param feedback {@link Enrollment}
	 * @return {@link Enrollment}
	 */
	Enrollment save(Enrollment enrollment);

	/**
	 * Busca un enrollment asociado a un email.
	 * @param email {@link String}
	 * @return {@link List}<{@link Enrollment}>
	 */
	List<Enrollment> findByEmail(String email);

	/**
	 * Busca un enrollment asociados a un rut.
	 * @param rut {@link String}
	 * @return {@link List}<{@link Enrollment}>
	 */
	List<Enrollment> findByRUT(String rut);

	/**
	 * Devuelve todos los enrollments de la BD.
	 * @return {@link List}<{@link Enrollment}>
	 */
	List<Enrollment> findAll();
}
