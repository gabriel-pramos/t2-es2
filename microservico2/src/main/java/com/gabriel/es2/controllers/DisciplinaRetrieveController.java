package com.gabriel.es2.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.business.DisciplinaService;
import com.gabriel.es2.models.Disciplina;
import com.gabriel.es2.models.Estudante;

@RestController
public class DisciplinaRetrieveController {
	@Autowired
	private DisciplinaService disciplinaService;

	@GetMapping("/disciplina")
	public List<Disciplina> getAllDisciplinas() {
		return disciplinaService.getAllDisciplinas();
	}

	@GetMapping("/disciplina/{codigo}")
	public Disciplina getDisciplina(@PathVariable("codigo") Integer codigo) {
		return disciplinaService.getDisciplina(codigo);
	}

	@GetMapping("/disciplina/{codigo}/estudante")
	public List<Estudante> getDesciplinaEstudantes(@PathVariable("codigo") Integer codigo) {
		return disciplinaService.getDesciplinaEstudantes(codigo);
	}

}
