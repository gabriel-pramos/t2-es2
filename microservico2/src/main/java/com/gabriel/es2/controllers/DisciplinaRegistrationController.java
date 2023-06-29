package com.gabriel.es2.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.business.DisciplinaService;
import com.gabriel.es2.models.Disciplina;

@RestController
public class DisciplinaRegistrationController {
	@Autowired
	private DisciplinaService disciplinaService;

	@PostMapping("/disciplina")
	public Disciplina registerDisciplina(@RequestBody Disciplina disciplina) {
		return disciplinaService.registerDisciplina(disciplina);
	}

}
