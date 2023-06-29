package com.gabriel.es2.controllers;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import com.gabriel.es2.business.UsuarioService;
import com.gabriel.es2.models.Usuario;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrar(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario, HttpServletResponse response) {
        String sessionID = usuarioService.login(usuario);
        if (sessionID == null) {
            response.setStatus(401);
            return null;
        }
        Cookie cookie = new Cookie("session", sessionID);
        response.addCookie(cookie);
        return usuario;
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("session", "");
        response.addCookie(cookie);
    }

    @GetMapping("/usuario")
    public Usuario getUsuario(@CookieValue(name = "session", required = false) String sessionID,
            HttpServletResponse response) {
        Usuario u = usuarioService.getUsuario(sessionID);
        if (u == null) {
            response.setStatus(401);
            return null;
        }
        return u;
    }
}
