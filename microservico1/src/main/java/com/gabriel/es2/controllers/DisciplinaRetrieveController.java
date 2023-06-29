package com.gabriel.es2.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.beans.Disciplina;
import com.gabriel.es2.beans.DisciplinaRegistration;
import com.gabriel.es2.beans.Estudante;

@RestController
public class DisciplinaRetrieveController {
	@Autowired
	private DisciplinaRegistration disciplinaRegistration;

	@GetMapping("/disciplina")
	public List<Disciplina> getAllDisciplinas() {
		Iterable<Disciplina> disciplinas = disciplinaRegistration.findAll();
		return (List<Disciplina>) disciplinas;
	}

	@GetMapping("/disciplina/{codigo}")
	public Disciplina getDisciplina(@PathVariable("codigo") Integer codigo) {
		Disciplina disciplina = disciplinaRegistration.findByCodigo(codigo);
		return disciplina;
	}

	@GetMapping("/disciplina/{codigo}/estudante")
	public List<Estudante> getDesciplinaEstudantes(@PathVariable("codigo") Integer codigo) {
		Disciplina disciplina = disciplinaRegistration.findByCodigo(codigo);
		return disciplina.getEstudantes();
	}

}
