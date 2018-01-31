package com.cencosud.middleware.enrollment.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.enrollment.model.Enrollment;
import com.cencosud.middleware.enrollment.service.EnrollmentMongoService;

/**
 * <h1>EnrollmentController</h1>
 * <p>Funciones expuestas por el webservice.</p>
 */
@RestController	
@RequestMapping(path="/enrollment", produces="application/json; charset=UTF-8")
public class EnrollmentController {
	
	@Autowired
	EnrollmentMongoService mongoService;
	
	/**
	 * Crea un enrollment nuevo.
	 * @param enrollment {@link Enrollment}
	 * @return {@link Enrollment}
	 */
	@RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Enrollment save(@RequestBody Enrollment enrollment) {
		enrollment.setDate(new Date());
		return mongoService.save(enrollment);		
	}

	/**
	 * Busca un Enrollment al proporcionar los datos necesarios, el email es un dato <br>
	 * requerido por lo que tiene que ser proporcionado.
	 * @param email {@link String}
	 * @return {@link Enrollment}
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Enrollment> findByEmail(@RequestParam(required=true) String email) {
		List<Enrollment> listaEnrollment =  mongoService.findByEmail(email);
		return listaEnrollment;
	}
//
//	/**
//	 * Busca todos los Enrollment en la BD.
//	 * @return {@link Enrollment}
//	 */
//	@RequestMapping(method = RequestMethod.GET)
//	public List<Enrollment> findAll() {
//		List<Enrollment> listaEnrollment =  mongoService.findAll();
//		return listaEnrollment-;
//	}
}
