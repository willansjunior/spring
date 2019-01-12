package com.example.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.dto.PessoaDTO;
import com.example.spring.service.PessoaService;

@RestController
@RequestMapping(value = "/api/pessoa")
public class PessoaResource {
	
	private PessoaService pessoaService;

	@Autowired
	public PessoaResource(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@RequestMapping(value = "",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PessoaDTO>> findAll() {
		return ResponseEntity.ok(pessoaService.findAll());
	}
	
	@RequestMapping(value = "/nome",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PessoaDTO>> findByNome(@RequestParam("nome") String nome) {
		return ResponseEntity.ok(pessoaService.findByNome(nome));
	}

}
