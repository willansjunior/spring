package com.example.spring;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.example.spring.dto.PessoaDTO;
import com.example.spring.model.Pessoa;
import com.example.spring.model.Unidade;
import com.example.spring.repository.PessoaRepository;
import com.example.spring.repository.UnidadeRepository;
import com.example.spring.service.PessoaService;
import com.example.spring.service.UnidadeService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {
	
//	@Mock
	PessoaRepository pessoaRepository;
	
//	@Mock
	UnidadeRepository unidadeRepository;
	
	PessoaService pessoaService;
	
	UnidadeService unidadeService;
	
	private List<PessoaDTO> listPessoa = new ArrayList<>();
	
	@Before
	public void inicializar() {
		pessoaRepository = EasyMock.createMock(PessoaRepository.class);
		unidadeRepository = EasyMock.createMock(UnidadeRepository.class);
		pessoaService = EasyMock.createMock(PessoaService.class);
		unidadeService = EasyMock.createMock(UnidadeService.class);
		gerarLista();
	}

	@Test
	public void findAll() {
		EasyMock.expect(pessoaService.findAll()).andReturn(listPessoa);
		EasyMock.replay(pessoaService);
		List<PessoaDTO> list = pessoaService.findAll();
		assertNotNull(list);
		EasyMock.verify(pessoaService);
	}
	
	@Test
	public void findByName() {
		EasyMock.expect(pessoaService.findByNome("Willans Firmo")).andReturn(listPessoa);
		EasyMock.replay(pessoaService);
		List<PessoaDTO> list = pessoaService.findByNome("Willans Firmo");
		assertNotNull(list);
		EasyMock.verify(pessoaService);
	}
	
	private void gerarLista() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo(12L);
		pessoa.setNome("Willans Firmo");
		pessoa.setSalario(974.20);
		
		Unidade unidade = new Unidade();
		unidade.setCodigo(465L);
		unidade.setNome("Unidade de teste mockito");
		pessoa.setUnidade(unidade);
		
		Pessoa pessoa2 = new Pessoa();
		pessoa2.setCodigo(12L);
		pessoa2.setNome("Willans Firmo");
		pessoa2.setSalario(974.20);
		
		pessoa2.setUnidade(unidade);
		
		listPessoa.add(new PessoaDTO(pessoa));
		listPessoa.add(new PessoaDTO(pessoa2));
	}

}
