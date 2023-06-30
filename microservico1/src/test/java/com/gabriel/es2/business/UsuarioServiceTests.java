package com.gabriel.es2.business;

import com.gabriel.es2.models.Usuario;
import com.gabriel.es2.repositories.UsuarioRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTests {
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRegistration usuarioRegistration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioService = new UsuarioService(usuarioRegistration);
    }

    @Test
    void registrar_ValidUsuario_ReturnsRegisteredUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setSenha("password");

        when(usuarioRegistration.save(usuario)).thenReturn(usuario);

        Usuario registeredUsuario = usuarioService.registrar(usuario);

        assertNotNull(registeredUsuario);
        assertEquals(usuario.getEmail(), registeredUsuario.getEmail());
        assertEquals(usuario.getSenha(), registeredUsuario.getSenha());

        verify(usuarioRegistration, times(1)).save(usuario);
    }

    @Test
    void login_ValidUsuario_ReturnsSessionID() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setSenha("password");

        when(usuarioRegistration.findByEmail(usuario.getEmail())).thenReturn(usuario);

        String sessionID = usuarioService.login(usuario);

        assertNotNull(sessionID);

        verify(usuarioRegistration, times(1)).findByEmail(usuario.getEmail());
    }

    @Test
    void login_InvalidUsuario_ReturnsNull() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setSenha("password");

        when(usuarioRegistration.findByEmail(usuario.getEmail())).thenReturn(null);

        String sessionID = usuarioService.login(usuario);

        assertNull(sessionID);

        verify(usuarioRegistration, times(1)).findByEmail(usuario.getEmail());
    }

    @Test
    void login_IncorrectPassword_ReturnsNull() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setSenha("password");

        Usuario usuarioLogado = new Usuario();
        usuarioLogado.setEmail("test@example.com");
        usuarioLogado.setSenha("incorrectPassword");

        when(usuarioRegistration.findByEmail(usuario.getEmail())).thenReturn(usuarioLogado);

        String sessionID = usuarioService.login(usuario);

        assertNull(sessionID);

        verify(usuarioRegistration, times(1)).findByEmail(usuario.getEmail());
    }

    @Test
    void getUsuario_ValidSessionID_ReturnsUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setSenha("password");
        usuario.setNome("Test");

        when(usuarioRegistration.findByEmail(usuario.getEmail())).thenReturn(usuario);

        String sessionID = usuarioService.login(usuario);

        Usuario retrievedUsuario = usuarioService.getUsuario(sessionID);

        assertNotNull(retrievedUsuario);
        assertEquals(usuario.getEmail(), retrievedUsuario.getEmail());
        assertEquals(usuario.getSenha(), retrievedUsuario.getSenha());
    }

    @Test
    void getUsuario_InvalidSessionID_ReturnsNull() {
        String sessionID = "invalidSessionID";

        Usuario retrievedUsuario = usuarioService.getUsuario(sessionID);

        assertNull(retrievedUsuario);
    }
}
