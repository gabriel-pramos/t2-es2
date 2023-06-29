package com.gabriel.es2.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.business.DisciplinaService;
import com.gabriel.es2.business.EstudanteService;
import com.gabriel.es2.models.Disciplina;
import com.gabriel.es2.models.Estudante;

@RestController
public class EstudanteRegistrationController {
	@Autowired
	private EstudanteService estudanteService;
	@Autowired
	private DisciplinaService disciplinaService;

	@PostMapping("/estudante")
	public Estudante registerEstudante(@RequestBody Estudante estudante) {
		return estudanteService.registerEstudante(estudante);
	}

	@PostMapping("/disciplina/{codigo}/estudante/{matricula}")
	public void registerEstudanteDisciplina(@PathVariable Integer matricula, @PathVariable Integer codigo) {
		Disciplina disciplina = disciplinaService.getDisciplina(codigo);
		Estudante estudante = estudanteService.getEstudante(matricula);
		disciplina.getEstudantes().add(estudante);
		disciplinaService.saveDisciplina(disciplina);
		return;
	}

}
