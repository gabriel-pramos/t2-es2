package com.gabriel.es2.controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.beans.Estudante;
import com.gabriel.es2.beans.EstudanteRegistration;

@RestController
public class EstudanteUpdateController {
	@Autowired
	private EstudanteRegistration estudanteRegistration;

	@PutMapping("/estudante/{matricula}")
	public Estudante updateStudentRecord(@PathVariable("matricula") Integer matricula, @RequestBody Estudante input) {
		Optional<Estudante> optionalEstudante = estudanteRegistration.findById(matricula);
		Estudante estudante = optionalEstudante.orElse(null);
		estudante.setNome(input.getNome());
		estudante.setDocumento(input.getDocumento());
		estudante.setEndereco(input.getEndereco());
		estudanteRegistration.save(estudante);
		return estudante;
	}

}
