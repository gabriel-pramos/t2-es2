package com.gabriel.es2.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.es2.models.Disciplina;
import com.gabriel.es2.models.Estudante;
import com.gabriel.es2.repositories.EstudanteRegistration;

@Service
public class EstudanteService {
    @Autowired
    private EstudanteRegistration estudanteRegistration;

    public void deleteStudentRecord(String regdNum) {
        estudanteRegistration.deleteById(Integer.parseInt(regdNum));
        return;
    }

    public List<Estudante> getAllEstudantes(String nome) {
        if (nome != null) {
            Iterable<Estudante> estudantes = estudanteRegistration.findByNomeContaining(nome);
            return (List<Estudante>) estudantes;
        }
        Iterable<Estudante> estudantes = estudanteRegistration.findAll();
        return (List<Estudante>) estudantes;
    }

    public Estudante getEstudante(Integer matricula) {
        return estudanteRegistration.findById(matricula).orElse(null);
    }

    public List<Disciplina> getEstudanteDisciplinas(Integer matricula) {
        return estudanteRegistration.findDisciplinasByMatricula(matricula);
    }

    public Estudante updateStudentRecord(Integer matricula, Estudante input) {
        Optional<Estudante> optionalEstudante = estudanteRegistration.findById(matricula);
        Estudante estudante = optionalEstudante.orElse(null);
        estudante.setNome(input.getNome());
        estudante.setDocumento(input.getDocumento());
        estudante.setEndereco(input.getEndereco());
        estudanteRegistration.save(estudante);
        return estudante;
    }

    public Estudante registerEstudante(Estudante estudante) {
        estudanteRegistration.save(estudante);
        return estudante;
    }
}
