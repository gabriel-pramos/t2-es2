package com.gabriel.es2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.beans.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRegistration usuarioRegistration;

    private Map<String, Usuario> sessoes;

    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody Usuario usuario) {
        usuarioRegistration.save(usuario);
        return usuario;
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario, HttpServletResponse response) {
        if (sessoes == null) {
            sessoes = new java.util.HashMap<String, Usuario>();
        }

        Usuario usuarioLogado = usuarioRegistration.findByEmail(usuario.getEmail());
        if (usuarioLogado != null && usuarioLogado.getSenha().equals(usuario.getSenha())) {
            String sessionID = java.util.UUID.randomUUID().toString();
            sessoes.put(sessionID, usuarioLogado);
            Cookie cookie = new Cookie("session", sessionID);
            response.addCookie(cookie);
        }
        response.setStatus(401);
        return null;
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("session", "");
        response.addCookie(cookie);
    }

    @GetMapping("/usuario")
    public Usuario getUsuario(HttpServletRequest request, HttpServletResponse response) {
        if (sessoes == null) {
            sessoes = new java.util.HashMap<String, Usuario>();
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                Usuario usuario = sessoes.get(cookie.getValue());
                if (usuario != null) {
                    return usuario;
                }
            }
        }
        response.setStatus(401);
        return null;
    }

}
