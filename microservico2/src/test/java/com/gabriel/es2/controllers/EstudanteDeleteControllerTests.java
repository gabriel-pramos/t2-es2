package com.gabriel.es2.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.gabriel.es2.business.EstudanteService;
import com.gabriel.es2.controllers.EstudanteDeleteController;

@WebMvcTest(EstudanteDeleteController.class)
public class EstudanteDeleteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstudanteService estudanteService;

    @Test
    public void deleteStudentRecordTest() throws Exception {
        String registro = "12345";

        doNothing().when(estudanteService).deleteStudentRecord(registro);

        mockMvc.perform(MockMvcRequestBuilders.delete("/estudante/{regdNum}", registro))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(estudanteService).deleteStudentRecord(registro);
    }
}