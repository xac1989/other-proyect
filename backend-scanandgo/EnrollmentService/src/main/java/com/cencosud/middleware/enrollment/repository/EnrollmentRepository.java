package com.cencosud.middleware.enrollment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cencosud.middleware.enrollment.model.Enrollment;

/**
 * 
 * <h1>EnrollmentRepository</h1>
 * <p>
 * Crea un repository con mongo para ejecutar las funciones contenidas en
 * {@link MongoRepository}<br>
 * y las que se definan en esta clases.
 * </p>
 */
public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {

	/**
	 * Busca todos los enrollment que contienen el mismo email
	 * 
	 * @param email {@link String}
	 * @return {@link List}<{@link Enrollment}>
	 */
	@Query("{ 'email' : ?0 }")
	List<Enrollment> findByEmail(String email);

	/**
	 * Busca todos los enrollment que contienen el mismo RUT
	 * 
	 * @param rut {@link String}
	 * @return {@link List}<{@link Enrollment}>
	 */
	@Query("{ 'rut' : ?0 }")
	List<Enrollment> findByRUT(String rut);

	/**
	 * Busca todos los enrollments.
	 * @return {@link List}<{@link Enrollment}>
	 */
	@Query(value = "{}")
	List<Enrollment> findAll();
}
