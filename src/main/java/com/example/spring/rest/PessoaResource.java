package com.example.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.Pessoa;
import com.example.spring.repository.PessoaRepository;

@RestController
@RequestMapping(value = "/api/pessoa")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@RequestMapping(value = "",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> teste() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return ResponseEntity.ok(pessoas);
	}

}
