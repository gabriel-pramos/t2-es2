package com.gabriel.es2.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.beans.Disciplina;
import com.gabriel.es2.beans.Estudante;
import com.gabriel.es2.beans.EstudanteRegistration;

@RestController
public class EstudanteRetrieveController {
	@Autowired
	private EstudanteRegistration estudanteRegistration;

	@GetMapping("/estudante")
	public List<Estudante> getAllEstudantes(@RequestParam(value = "nome", required = false) String nome) {
		if (nome != null) {
			Iterable<Estudante> estudantes = estudanteRegistration.findByNomeContaining(nome);
			return (List<Estudante>) estudantes;
		}
		Iterable<Estudante> estudantes = estudanteRegistration.findAll();
		return (List<Estudante>) estudantes;
	}

	@GetMapping("/estudante/{matricula}")
	public Estudante getEstudante(@PathVariable("matricula") Integer matricula) {
		return estudanteRegistration.findById(matricula).orElse(null);
	}

	@GetMapping("/estudante/{matricula}/disciplina")
	public List<Disciplina> getEstudanteDisciplinas(@PathVariable("matricula") Integer matricula) {
		return estudanteRegistration.findDisciplinasByMatricula(matricula);
	}

}
