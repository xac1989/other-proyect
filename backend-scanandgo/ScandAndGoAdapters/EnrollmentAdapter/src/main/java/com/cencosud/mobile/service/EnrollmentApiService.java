package com.cencosud.mobile.service;

import com.cencosud.middleware.enrollment.model.Enrollment;
import com.cencosud.mobile.exceptions.NotFoundException;

public interface EnrollmentApiService {
	Enrollment product(Enrollment enrollment) throws NotFoundException;
}
