package com.example.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.dto.PessoaDTO;
import com.example.spring.model.Pessoa;
import com.example.spring.repository.PessoaRepository;

@Service
public class PessoaService {
	
	private PessoaRepository pessoaRepository;
	
	@Autowired
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
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
		Pessoa pessoa = new Pessoa(dto);
		pessoaRepository.save(pessoa);
		
		return new PessoaDTO(pessoa);
	}

}
