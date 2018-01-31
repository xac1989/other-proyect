package com.cencosud.middleware.feedback.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cencosud.middleware.feedback.model.Feedback;

/**
 * 
 * <h1>FeedbackRepository</h1>
 * <p>
 * Crea un repository con mongo para ejecutar las funciones contenidas en
 * {@link MongoRepository}<br>
 * y las que se definan en esta clases.
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Mar 22, 2017
 */
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

	/**
	 * Busca todos los feedbacks que contienen el mismo email
	 * 
	 * @param email {@link String}
	 * @return {@link List}<{@link Feedback}>
	 */
	@Query("{ 'email' : ?0 }")
	List<Feedback> findByEmail(String email);

	/**
	 * Busca todos los feedbacks.
	 * @return {@link List}<{@link Feedback}>
	 */
	@Query(value = "{}")
	List<Feedback> findAll();
}
