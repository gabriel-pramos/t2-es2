package com.gabriel.es2.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.beans.*;

@RestController
public class EstudanteRegistrationController {
	@Autowired
	private EstudanteRegistration estudanteRegistration;
	@Autowired
	private DisciplinaRegistration disciplinaRegistration;

	@PostMapping("/estudante")
	public Estudante registerEstudante(@RequestBody Estudante estudante) {
		estudanteRegistration.save(estudante);
		return estudante;
	}

	@PostMapping("/disciplina/{codigo}/estudante/{matricula}")
	public void registerEstudanteDisciplina(@PathVariable Integer matricula, @PathVariable Integer codigo) {
		Disciplina disciplina = disciplinaRegistration.findByCodigo(codigo);
		Estudante estudante = estudanteRegistration.findById(matricula).orElse(null);
		disciplina.getEstudantes().add(estudante);
		disciplinaRegistration.save(disciplina);
		return;
	}

}
