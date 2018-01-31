package com.cencosud.middleware.feedback.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cencosud.middleware.feedback.model.Feedback;
import com.cencosud.middleware.feedback.service.FeedbackMongoService;

/**
 * <h1>FeedbackController</h1>
 * <p>Funciones expuestas por el webservice.</p>
 */
@RestController	
@RequestMapping(path="/feedback", produces="application/json; charset=UTF-8")
public class FeedbackController {
	
	@Autowired
	FeedbackMongoService mongoService;
	
	/**
	 * Crea un feedback nuevo.
	 * @param feedback {@link Feedback}
	 * @return {@link Feedback}
	 */
	@RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public Feedback save(@RequestBody Feedback feedback) {
		feedback.setDate(new Date());
		return mongoService.save(feedback);		
	}

	/**
	 * Busca un feedbacko al proporcionar los datos necesarios, el ean es un dato <br>
	 * requerido por lo que tiene que ser proporcionado.
	 * @param email {@link String}
	 * @return {@link Feedback}
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Feedback> findByEmail(@RequestParam(required=true) String email) {
		List<Feedback> listaFeedback =  mongoService.findByEmail(email);
		return listaFeedback;
	}
//
//	/**
//	 * Busca todos los feedback en la BD.
//	 * @return {@link Feedback}
//	 */
//	@RequestMapping(method = RequestMethod.GET)
//	public List<Feedback> findAll() {
//		List<Feedback> listaFeedback =  mongoService.findAll();
//		return listaFeedback;
//	}
}
