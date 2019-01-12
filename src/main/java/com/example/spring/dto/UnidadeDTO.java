package com.example.spring.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.example.spring.model.Unidade;

@SuppressWarnings("serial")
public class UnidadeDTO implements Serializable {
	
	@NotNull(message = "Código obrigatorio!")
	public Long codigo;
	
	@NotNull(message = "Nome obrigatorio!")
	public String nome;
	
	public UnidadeDTO() {
		
	}
	
	public UnidadeDTO(Unidade unidade) {
		this.codigo = unidade.getCodigo();
		this.nome = unidade.getNome();
	}

}
