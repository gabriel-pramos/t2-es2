package com.gabriel.es2.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.beans.EstudanteRegistration;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class EstudanteDeleteController {
	@Autowired
	private EstudanteRegistration estudanteRegistration;

	@DeleteMapping("/estudante/{regdNum}")
	public void deleteStudentRecord(@PathVariable("regdNum") String regdNum) {
		estudanteRegistration.deleteById(Integer.parseInt(regdNum));
		return;
	}

}
