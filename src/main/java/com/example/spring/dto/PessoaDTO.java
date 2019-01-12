package com.example.spring.dto;

import java.io.Serializable;

import com.example.spring.model.Pessoa;

@SuppressWarnings("serial")
public class PessoaDTO implements Serializable {
	
	public Long codigo;
	public String nome;
	public double salario;
	public UnidadeDTO unidade;
	
	public PessoaDTO() {
		
	}
	
	public PessoaDTO(Pessoa pessoa) {
		this.codigo = pessoa.getCodigo();
		this.nome = pessoa.getNome();
		this.salario = pessoa.getSalario();
	}

}
