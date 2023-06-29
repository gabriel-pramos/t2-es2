package com.gabriel.es2.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.business.EstudanteService;
import com.gabriel.es2.models.Disciplina;
import com.gabriel.es2.models.Estudante;

@RestController
public class EstudanteRetrieveController {
	@Autowired
	private EstudanteService estudanteService;

	@GetMapping("/estudante")
	public List<Estudante> getAllEstudantes(@RequestParam(value = "nome", required = false) String nome) {
		return estudanteService.getAllEstudantes(nome);
	}

	@GetMapping("/estudante/{matricula}")
	public Estudante getEstudante(@PathVariable("matricula") Integer matricula) {
		return estudanteService.getEstudante(matricula);
	}

	@GetMapping("/estudante/{matricula}/disciplina")
	public List<Disciplina> getEstudanteDisciplinas(@PathVariable("matricula") Integer matricula) {
		return estudanteService.getEstudanteDisciplinas(matricula);
	}

}
