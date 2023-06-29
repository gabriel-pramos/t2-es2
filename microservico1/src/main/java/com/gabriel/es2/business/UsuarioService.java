package com.gabriel.es2.business;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.es2.models.Usuario;
import com.gabriel.es2.repositories.UsuarioRegistration;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRegistration usuarioRegistration;

    private Map<String, Usuario> sessoes;

    public UsuarioService() {
        sessoes = new java.util.HashMap<String, Usuario>();
    }

    public Usuario registrar(Usuario usuario) {
        usuarioRegistration.save(usuario);
        return usuario;
    }

    // public Usuario login(Usuario usuario, HttpServletResponse response) {
    public String login(Usuario usuario) {
        Usuario usuarioLogado = usuarioRegistration.findByEmail(usuario.getEmail());
        if (usuarioLogado != null && usuarioLogado.getSenha().equals(usuario.getSenha())) {
            String sessionID = java.util.UUID.randomUUID().toString();
            sessoes.put(sessionID, usuarioLogado);
            return sessionID;
        }
        return null;
    }

    public Usuario getUsuario(String sessionID) {
        Usuario usuario = sessoes.get(sessionID);
        if (usuario != null) {
            return usuario;
        }
        return null;
    }

}
