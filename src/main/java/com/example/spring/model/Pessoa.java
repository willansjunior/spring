package com.example.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.spring.dto.PessoaDTO;

@Entity
@Table(name = "pessoa")
@SuppressWarnings("serial")
public class Pessoa implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "salario", nullable = false)
	private double salario;
	
	@ManyToOne
	@JoinColumn(name = "unidade", nullable = false)
	private Unidade unidade;
	
	public Pessoa() {
		
	}

	public Pessoa(PessoaDTO dto) {
		this.codigo = dto.codigo;
		this.nome = dto.nome;
		this.salario = dto.salario;
		this.unidade = new Unidade(dto.unidade);
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
}
