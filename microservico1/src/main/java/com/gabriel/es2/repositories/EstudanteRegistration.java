package com.gabriel.es2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabriel.es2.models.Disciplina;
import com.gabriel.es2.models.Estudante;

@Repository
public interface EstudanteRegistration extends CrudRepository<Estudante, Integer> {
    @Query("SELECT e FROM Estudante e WHERE e.nome LIKE %:nome%")
    List<Estudante> findByNomeContaining(@Param("nome") String nome);

    @Query("SELECT d FROM Disciplina d JOIN d.estudantes e WHERE e.matricula = :matricula")
    List<Disciplina> findDisciplinasByMatricula(@Param("matricula") Integer matricula);
}
