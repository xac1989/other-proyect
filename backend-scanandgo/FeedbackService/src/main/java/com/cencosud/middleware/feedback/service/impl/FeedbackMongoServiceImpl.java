package com.cencosud.middleware.feedback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cencosud.middleware.feedback.model.Feedback;
import com.cencosud.middleware.feedback.repository.FeedbackRepository;
import com.cencosud.middleware.feedback.service.FeedbackMongoService;

@Service
public class FeedbackMongoServiceImpl implements FeedbackMongoService {

	@Autowired
	private FeedbackRepository repository;

	@Override
	public Feedback save(Feedback feedback) {
		return repository.save(feedback);
	}

	@Override
	public List<Feedback> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Feedback> findByEmail(String email) {
		return repository.findByEmail(email);
	}

}
