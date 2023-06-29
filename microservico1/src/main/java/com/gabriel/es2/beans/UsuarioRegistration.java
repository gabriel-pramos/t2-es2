package com.gabriel.es2.beans;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRegistration extends CrudRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findByEmail(String email);
}
