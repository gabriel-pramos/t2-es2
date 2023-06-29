package com.gabriel.es2.beans;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
public class Disciplina {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer codigo;
	private Integer turma;
	private String nome;
	private String horario;

	@ManyToMany
	@JoinTable(name = "disciplina_estudante", joinColumns = @JoinColumn(name = "disciplina_id"), inverseJoinColumns = @JoinColumn(name = "estudante_matricula"))
	private List<Estudante> estudantes;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getTurma() {
		return this.turma;
	}

	public void setTurma(Integer turma) {
		this.turma = turma;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHorario() {
		return this.horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public java.util.List<Estudante> getEstudantes() {
		return this.estudantes;
	}

	public void setEstudantes(java.util.List<Estudante> estudantes) {
		this.estudantes = estudantes;
	}

}
