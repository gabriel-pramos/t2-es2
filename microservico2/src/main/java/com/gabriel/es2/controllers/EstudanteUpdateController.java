package com.gabriel.es2.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.business.EstudanteService;
import com.gabriel.es2.models.Estudante;

@RestController
public class EstudanteUpdateController {
	@Autowired
	private EstudanteService estudanteService;

	@PutMapping("/estudante/{matricula}")
	public Estudante updateStudentRecord(@PathVariable("matricula") Integer matricula, @RequestBody Estudante input) {
		return estudanteService.updateStudentRecord(matricula, input);
	}

}
