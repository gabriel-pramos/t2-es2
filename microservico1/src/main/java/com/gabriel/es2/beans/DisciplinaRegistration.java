package com.gabriel.es2.beans;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRegistration extends CrudRepository<Disciplina, Integer> {
    @Query("SELECT d FROM Disciplina d WHERE d.codigo = :codigo")
    Disciplina findByCodigo(Integer codigo);
}
