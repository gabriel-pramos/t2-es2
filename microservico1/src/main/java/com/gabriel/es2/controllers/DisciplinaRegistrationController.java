package com.gabriel.es2.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.beans.*;

@RestController
public class DisciplinaRegistrationController {
	@Autowired
	private DisciplinaRegistration disciplinaRegistration;

	@PostMapping("/disciplina")
	public Disciplina registerDisciplina(@RequestBody Disciplina disciplina) {
		disciplinaRegistration.save(disciplina);
		return disciplina;
	}

}
