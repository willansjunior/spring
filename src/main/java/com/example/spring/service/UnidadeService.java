package com.example.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.ErrorException;
import com.example.spring.dto.RelatorioUnidadeQtdPessoaDTO;
import com.example.spring.dto.RelatorioUnidadeTotalSalarioDTO;
import com.example.spring.dto.UnidadeDTO;
import com.example.spring.model.Unidade;
import com.example.spring.repository.UnidadeRepository;

@Service
public class UnidadeService {
	
	private UnidadeRepository unidadeRepository;
	
	@Autowired
	public UnidadeService(UnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}
	
	@Transactional
	public UnidadeDTO create(UnidadeDTO dto) {
		Unidade unidade = unidadeRepository.findOne(dto.codigo);
		if (unidade == null) {
			unidade = new Unidade(dto);
			unidadeRepository.save(unidade);
		} else {
			throw new ErrorException("Já existe uma unidade cadastrada com o codigo " + dto.codigo + "!");
		}
		
		return new UnidadeDTO(unidade);
	}
	
	@Transactional
	public List<RelatorioUnidadeQtdPessoaDTO> findAllUnidadesQtdPessoa() {
		List<RelatorioUnidadeQtdPessoaDTO> result = unidadeRepository.findUnidadeQtdPessoa();
		
		return result;
	}
	
	@Transactional
	public List<RelatorioUnidadeTotalSalarioDTO> findAllUnidadesTotalSalario() {
		List<RelatorioUnidadeTotalSalarioDTO> result = unidadeRepository.findUnidadeTotalSalario();
		
		return result;
	}
	
}
