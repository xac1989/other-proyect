package com.cencosud.mobile.service;

import com.cencosud.middleware.feedback.model.Feedback;
import com.cencosud.mobile.exceptions.NotFoundException;

public interface FeedbackApiService {
	Feedback product(Feedback feedback) throws NotFoundException;
}
