package com.example.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.dto.UnidadeDTO;
import com.example.spring.service.UnidadeService;

@RestController
@RequestMapping(value = "/api/unidade")
public class UnidadeController {
	
	private UnidadeService unidadeService;
	
	@Autowired
	public UnidadeController(UnidadeService unidadeService) {
		this.unidadeService = unidadeService;
	}
	
	@RequestMapping(value = "",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UnidadeDTO> create(@Validated @RequestBody UnidadeDTO dto) {
		return ResponseEntity.ok(unidadeService.create(dto));
	}

}
