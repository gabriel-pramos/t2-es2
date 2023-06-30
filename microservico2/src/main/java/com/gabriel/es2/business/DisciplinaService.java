package com.gabriel.es2.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.es2.models.Disciplina;
import com.gabriel.es2.models.Estudante;
import com.gabriel.es2.repositories.DisciplinaRegistration;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRegistration disciplinaRegistration;

    public DisciplinaService(DisciplinaRegistration disciplinaRegistration) {
        this.disciplinaRegistration = disciplinaRegistration;
    }

    public Disciplina registerDisciplina(Disciplina disciplina) {
        disciplinaRegistration.save(disciplina);
        return disciplina;
    }

    public List<Disciplina> getAllDisciplinas() {
        Iterable<Disciplina> disciplinas = disciplinaRegistration.findAll();
        return (List<Disciplina>) disciplinas;
    }

    public Disciplina getDisciplina(Integer codigo) {
        Disciplina disciplina = disciplinaRegistration.findByCodigo(codigo);
        return disciplina;
    }

    public List<Estudante> getDesciplinaEstudantes(Integer codigo) {
        Disciplina disciplina = disciplinaRegistration.findByCodigo(codigo);
        return disciplina.getEstudantes();
    }

    public void saveDisciplina(Disciplina disciplina) {
        disciplinaRegistration.save(disciplina);
    }
}
