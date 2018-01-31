package com.cencosud.middleware.feedback.service;

import java.util.List;

import com.cencosud.middleware.feedback.model.Feedback;

public interface FeedbackMongoService {
	
	/**
	 * Crea un feedback.
	 * @param feedback {@link Feedback}
	 * @return {@link Feedback}
	 */
	Feedback save(Feedback feedback);

	/**
	 * Busca un feedback asociado a un email.
	 * @param email {@link String}
	 * @return {@link List}<{@link Feedback}>
	 */
	List<Feedback> findByEmail(String email);

	/**
	 * Devuelve todos los feedbacks de la BD.
	 * @return {@link List}<{@link Feedback}>
	 */
	List<Feedback> findAll();
}
