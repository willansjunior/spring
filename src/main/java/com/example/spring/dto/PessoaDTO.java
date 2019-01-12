package com.example.spring.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.example.spring.model.Pessoa;

@SuppressWarnings("serial")
public class PessoaDTO implements Serializable {
	
	@NotNull(message = "Código obrigatorio!")
	public Long codigo;
	
	@NotNull(message = "Nome obrigatorio!")
	public String nome;
	
	@NotNull(message = "Salario obrigatorio!")
	public Double salario;
	
	@NotNull(message = "A unidade deve ser informada!")
	public UnidadeDTO unidade;
	
	public PessoaDTO() {
		
	}
	
	public PessoaDTO(Pessoa pessoa) {
		this.codigo = pessoa.getCodigo();
		this.nome = pessoa.getNome();
		this.salario = pessoa.getSalario();
		this.unidade = new UnidadeDTO(pessoa.getUnidade());
	}

}
