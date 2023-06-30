package com.gabriel.es2.business;

import com.gabriel.es2.models.Disciplina;
import com.gabriel.es2.models.Estudante;
import com.gabriel.es2.repositories.DisciplinaRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DisciplinaServiceTest {

    @Mock
    private DisciplinaRegistration disciplinaRegistration;

    private DisciplinaService disciplinaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        disciplinaService = new DisciplinaService(disciplinaRegistration);
    }

    @Test
    void registerDisciplina_ValidDisciplina_ReturnsRegisteredDisciplina() {
        Disciplina disciplina = new Disciplina();

        when(disciplinaRegistration.save(disciplina)).thenReturn(disciplina);

        Disciplina registeredDisciplina = disciplinaService.registerDisciplina(disciplina);

        assertEquals(disciplina, registeredDisciplina);
        verify(disciplinaRegistration, times(1)).save(disciplina);
    }

    @Test
    void getAllDisciplinas_ReturnsListOfDisciplinas() {
        List<Disciplina> expectedDisciplinas = new ArrayList<>();
        expectedDisciplinas.add(new Disciplina());
        expectedDisciplinas.add(new Disciplina());

        when(disciplinaRegistration.findAll()).thenReturn(expectedDisciplinas);

        List<Disciplina> disciplinas = disciplinaService.getAllDisciplinas();

        assertEquals(expectedDisciplinas, disciplinas);
        verify(disciplinaRegistration, times(1)).findAll();
    }

    @Test
    void getDisciplina_ExistingCodigo_ReturnsDisciplina() {
        int codigo = 1;
        Disciplina expectedDisciplina = new Disciplina();

        when(disciplinaRegistration.findByCodigo(codigo)).thenReturn(expectedDisciplina);

        Disciplina disciplina = disciplinaService.getDisciplina(codigo);

        assertEquals(expectedDisciplina, disciplina);
        verify(disciplinaRegistration, times(1)).findByCodigo(codigo);
    }

    @Test
    void getDesciplinaEstudantes_ExistingCodigo_ReturnsEstudantes() {
        int codigo = 1;
        Disciplina disciplina = new Disciplina();
        List<Estudante> expectedEstudantes = new ArrayList<>();
        expectedEstudantes.add(new Estudante());
        expectedEstudantes.add(new Estudante());

        disciplina.setEstudantes(expectedEstudantes);

        when(disciplinaRegistration.findByCodigo(codigo)).thenReturn(disciplina);

        List<Estudante> estudantes = disciplinaService.getDesciplinaEstudantes(codigo);

        assertEquals(expectedEstudantes, estudantes);
        verify(disciplinaRegistration, times(1)).findByCodigo(codigo);
    }

    @Test
    void saveDisciplina_ValidDisciplina_CallsSaveMethod() {
        Disciplina disciplina = new Disciplina();

        disciplinaService.saveDisciplina(disciplina);

        verify(disciplinaRegistration, times(1)).save(disciplina);
    }
}
