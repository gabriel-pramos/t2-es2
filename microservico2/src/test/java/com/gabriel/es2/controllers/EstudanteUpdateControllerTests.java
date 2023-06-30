package com.gabriel.es2.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.gabriel.es2.business.EstudanteService;
import com.gabriel.es2.controllers.EstudanteUpdateController;
import com.gabriel.es2.models.Estudante;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(EstudanteUpdateController.class)
public class EstudanteUpdateControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstudanteService estudanteService;

    @Test
    public void UpdateStudentRecordTest() throws Exception {
        int matricula = 123;
        Estudante aluno = new Estudante();
        aluno.setNome("João");

        Estudante alunoatt = new Estudante();
        alunoatt.setMatricula(matricula);
        alunoatt.setNome("João Silva");

        when(estudanteService.updateStudentRecord(eq(matricula), any(Estudante.class))).thenReturn(alunoatt);

        mockMvc.perform(MockMvcRequestBuilders.put("/estudante/{matricula}", matricula)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"João Silva\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricula").value(matricula))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("João Silva"));
    }
}