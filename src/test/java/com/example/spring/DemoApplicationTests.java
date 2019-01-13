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
import com.example.spring.dto.RelatorioUnidadeQtdPessoaDTO;
import com.example.spring.dto.RelatorioUnidadeTotalSalarioDTO;
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
	
	private List<RelatorioUnidadeQtdPessoaDTO> listQtdPessoaPorUnidade = new ArrayList<>();
	
	private List<RelatorioUnidadeTotalSalarioDTO> listTotalSalarioPorUnidade = new ArrayList<>();
	
	@Before
	public void inicializar() {
		pessoaRepository = EasyMock.createMock(PessoaRepository.class);
		unidadeRepository = EasyMock.createMock(UnidadeRepository.class);
		pessoaService = EasyMock.createMock(PessoaService.class);
		unidadeService = EasyMock.createMock(UnidadeService.class);
		gerarLista();
		gerarUnidadeQtdPessoa();
		gerarUnidadeSalario();
	}

	@Test
	public void findAllPessoa() {
		EasyMock.expect(pessoaService.findAll()).andReturn(listPessoa);
		EasyMock.replay(pessoaService);
		List<PessoaDTO> list = pessoaService.findAll();
		assertNotNull(list);
		EasyMock.verify(pessoaService);
	}
	
	@Test
	public void findByNamePessoa() {
		EasyMock.expect(pessoaService.findByNome("Willans Firmo")).andReturn(listPessoa);
		EasyMock.replay(pessoaService);
		List<PessoaDTO> list = pessoaService.findByNome("Willans Firmo");
		assertNotNull(list);
		EasyMock.verify(pessoaService);
	}
	
	@Test
	public void findTotalSalarioPorUnidade() {
		EasyMock.expect(unidadeService.findAllUnidadesTotalSalario()).andReturn(listTotalSalarioPorUnidade);
		EasyMock.replay(unidadeService);
		List<RelatorioUnidadeTotalSalarioDTO> list = unidadeService.findAllUnidadesTotalSalario();
		assertNotNull(list);
		EasyMock.verify(unidadeService);
	}
	
	@Test
	public void findRelatorioQtdPessoaPorUnidade() {
		EasyMock.expect(unidadeService.findAllUnidadesQtdPessoa()).andReturn(listQtdPessoaPorUnidade);
		EasyMock.replay(unidadeService);
		List<RelatorioUnidadeQtdPessoaDTO> list = unidadeService.findAllUnidadesQtdPessoa();
		assertNotNull(list);
		EasyMock.verify(unidadeService);
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
		
		listPessoa.add(new PessoaDTO(pessoa));
	}
	
	private void gerarUnidadeQtdPessoa() {
		RelatorioUnidadeQtdPessoaDTO relatiro = new RelatorioUnidadeQtdPessoaDTO();
		relatiro.nome = "Unidade Mock";
		relatiro.qtdPessoa = 3;
		
		listQtdPessoaPorUnidade.add(relatiro);
	}
	
	private void gerarUnidadeSalario() {
		RelatorioUnidadeTotalSalarioDTO relatorio = new RelatorioUnidadeTotalSalarioDTO();
		relatorio.nome = "Unidade Mock";
		relatorio.totalSalario = 895.10;
		
		listTotalSalarioPorUnidade.add(relatorio);
	}

}
