package com.gabriel.es2.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.gabriel.es2.models.Usuario;
import com.gabriel.es2.repositories.UsuarioRegistration;

public class UsuarioServiceTest {

    private Usuario user;

    @Mock
    private UsuarioRegistration userRegistrationMock;

    private UsuarioService userService;

    @BeforeEach
    public void setUp() throws Exception{
        user = new Usuario(/*1,"Marcioteste@email.com", "Marcio","abc123"*/);
        user.setId(1);
        user.setNome("Marcio");
        user.setEmail("Marcioteste@email.com");
        user.setSenha("abc123");

    }

    @Test
    public void registrar(){
        Usuario aux = new Usuario();
        aux.setId(user.getId());
        aux.setEmail(user.getEmail());
        aux.setNome(user.getNome());
        aux.setSenha(user.getSenha());
        assertEquals(aux, user);

    }

    /*@Test
    public void loginTest(){
        Usuario aux = new Usuario();
        aux.setEmail(user.getEmail());
        aux.setSenha(user.getSenha());

        Mockito.when(userRegistrationMock.findByEmail(aux.getEmail())).thenReturn(aux);

        String sessionID = userService.login(aux);

        assertNotNull(sessionID);
    }*/

    @Test
    public void getUsuarioTest(){
        String res = user.getNome();
        assertEquals("Marcio", res);
    }
        
}
