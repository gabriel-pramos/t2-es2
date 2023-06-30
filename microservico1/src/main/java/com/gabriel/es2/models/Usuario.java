package com.gabriel.es2.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String email;
	private String nome;
	private String senha;

	/*public Usuario(int id, String email, String nome, String senha){
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}*/

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id) &&
			Objects.equals(email, other.email) &&
			Objects.equals(nome, other.nome) &&
			Objects.equals(senha, other.senha);
	}

}