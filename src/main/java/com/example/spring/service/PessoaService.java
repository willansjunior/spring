package com.example.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.ErrorException;
import com.example.spring.dto.PessoaDTO;
import com.example.spring.model.Pessoa;
import com.example.spring.model.Unidade;
import com.example.spring.repository.PessoaRepository;
import com.example.spring.repository.UnidadeRepository;

@Service
public class PessoaService {
	
	private PessoaRepository pessoaRepository;
	private UnidadeRepository unidadeRepository;
	
	@Autowired
	public PessoaService(PessoaRepository pessoaRepository,
			UnidadeRepository unidadeRepository) {
		this.pessoaRepository = pessoaRepository;
		this.unidadeRepository = unidadeRepository;
	}

	@Transactional
	public List<PessoaDTO> findAll() {
		List<PessoaDTO> result = new ArrayList<>();
		for (Pessoa pessoa : pessoaRepository.findAll()) {
			result.add(new PessoaDTO(pessoa));
		}
		return result;
	}

	@Transactional
	public List<PessoaDTO> findByNome(String nome) {
		List<PessoaDTO> result = new ArrayList<>();
		for (Pessoa pessoa : pessoaRepository.findByNome(nome)) {
			result.add(new PessoaDTO(pessoa));
		}
		return result;
	}
	
	@Transactional
	public PessoaDTO create(PessoaDTO dto) {
		Unidade unidade = unidadeRepository.findOne(dto.unidade.codigo);
		if (unidade == null) {
			throw new ErrorException("A unidade de codigo " + dto.unidade.codigo + " não existe!");
		}
		Pessoa pessoa = pessoaRepository.findOne(dto.codigo);
		if (pessoa == null) {
			pessoa = new Pessoa(dto);
			pessoaRepository.save(pessoa);
		} else {
			throw new ErrorException("Já existe uma pessoa com o codigo " + dto.codigo + "!");
		}
		
		return new PessoaDTO(pessoa);
	}

}
