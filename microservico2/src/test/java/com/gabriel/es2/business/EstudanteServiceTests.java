package com.gabriel.es2.business;

import com.gabriel.es2.models.Disciplina;
import com.gabriel.es2.models.Estudante;
import com.gabriel.es2.repositories.EstudanteRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstudanteServiceTest {
    private EstudanteService estudanteService;

    @Mock
    private EstudanteRegistration estudanteRegistration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        estudanteService = new EstudanteService(estudanteRegistration);
    }

    @Test
    void deleteStudentRecord_ValidRegdNum_DeletesStudentRecord() {
        String regdNum = "12345";

        estudanteService.deleteStudentRecord(regdNum);

        verify(estudanteRegistration, times(1)).deleteById(12345);
    }

    @Test
    void getAllEstudantes_WithNameFilter_ReturnsFilteredEstudanteList() {
        String nome = "John";
        Estudante estudante1 = new Estudante(1, "John Doe", "12345", "Address 1");
        Estudante estudante2 = new Estudante(2, "John Smith", "67890", "Address 2");
        List<Estudante> expectedEstudantes = Arrays.asList(estudante1, estudante2);

        when(estudanteRegistration.findByNomeContaining(nome)).thenReturn(expectedEstudantes);

        List<Estudante> estudantes = estudanteService.getAllEstudantes(nome);

        assertEquals(expectedEstudantes, estudantes);
        verify(estudanteRegistration, times(1)).findByNomeContaining(nome);
    }

    @Test
    void getAllEstudantes_WithoutNameFilter_ReturnsAllEstudantes() {
        Estudante estudante1 = new Estudante(1, "John Doe", "12345", "Address 1");
        Estudante estudante2 = new Estudante(2, "Jane Smith", "67890", "Address 2");
        List<Estudante> expectedEstudantes = Arrays.asList(estudante1, estudante2);

        when(estudanteRegistration.findAll()).thenReturn(expectedEstudantes);

        List<Estudante> estudantes = estudanteService.getAllEstudantes(null);

        assertEquals(expectedEstudantes, estudantes);
        verify(estudanteRegistration, times(1)).findAll();
    }

    @Test
    void getEstudante_ExistingMatricula_ReturnsEstudante() {
        int matricula = 1;
        Estudante expectedEstudante = new Estudante(matricula, "John Doe", "12345", "Address 1");

        when(estudanteRegistration.findById(matricula)).thenReturn(Optional.of(expectedEstudante));

        Estudante estudante = estudanteService.getEstudante(matricula);

        assertEquals(expectedEstudante, estudante);
        verify(estudanteRegistration, times(1)).findById(matricula);
    }

    @Test
    void getEstudante_NonExistingMatricula_ReturnsNull() {
        int matricula = 1;

        when(estudanteRegistration.findById(matricula)).thenReturn(Optional.empty());

        Estudante estudante = estudanteService.getEstudante(matricula);

        assertNull(estudante);
        verify(estudanteRegistration, times(1)).findById(matricula);
    }

    @Test
    void getEstudanteDisciplinas_ExistingMatricula_ReturnsDisciplinas() {
        int matricula = 1;
        Disciplina disciplina1 = new Disciplina();
        Disciplina disciplina2 = new Disciplina();
        List<Disciplina> expectedDisciplinas = Arrays.asList(disciplina1, disciplina2);

        when(estudanteRegistration.findDisciplinasByMatricula(matricula)).thenReturn(expectedDisciplinas);

        List<Disciplina> disciplinas = estudanteService.getEstudanteDisciplinas(matricula);

        assertEquals(expectedDisciplinas, disciplinas);
        verify(estudanteRegistration, times(1)).findDisciplinasByMatricula(matricula);
    }

    @Test
    void updateStudentRecord_ExistingMatricula_ReturnsUpdatedEstudante() {
        int matricula = 1;
        Estudante input = new Estudante();
        input.setNome("John Doe");
        input.setDocumento("12345");
        input.setEndereco("Address 1");

        Estudante existingEstudante = new Estudante(matricula, "Old Name", "67890", "Old Address");
        Estudante expectedEstudante = new Estudante(matricula, input.getNome(), input.getDocumento(),
                input.getEndereco());

        when(estudanteRegistration.findById(matricula)).thenReturn(Optional.of(existingEstudante));
        when(estudanteRegistration.save(existingEstudante)).thenReturn(expectedEstudante);

        Estudante updatedEstudante = estudanteService.updateStudentRecord(matricula, input);

        assertNotNull(updatedEstudante);
        assertNotEquals(expectedEstudante, updatedEstudante);
        verify(estudanteRegistration, times(1)).findById(matricula);
        verify(estudanteRegistration, times(1)).save(existingEstudante);
    }

    @Test
    void registerEstudante_ValidEstudante_ReturnsRegisteredEstudante() {
        Estudante estudante = new Estudante();
        estudante.setNome("John Doe");
        estudante.setDocumento("12345");
        estudante.setEndereco("Address 1");

        when(estudanteRegistration.save(estudante)).thenReturn(estudante);

        Estudante registeredEstudante = estudanteService.registerEstudante(estudante);

        assertNotNull(registeredEstudante);
        assertEquals(estudante, registeredEstudante);
        verify(estudanteRegistration, times(1)).save(estudante);
    }
}
