package com.example.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
import com.example.spring.dto.UnidadeDTO;
import com.example.spring.model.Pessoa;
import com.example.spring.model.Unidade;
import com.example.spring.repository.PessoaRepository;
import com.example.spring.repository.UnidadeRepository;
import com.example.spring.service.PessoaService;
import com.example.spring.service.UnidadeService;

/**
 * Classe utilizada para os testes unitarios de Pessoa e Unidade
 * @author willans firmo
 * @since 12/01/2019
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {
	
	PessoaRepository pessoaRepository;
	
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
		
		/**
		 * Montando a lista de pessoas
		 */
		gerarLista();
		
		/**
		 * Montando a lista de unidade com quantidade de pessoas
		 */
		gerarUnidadeQtdPessoa();
		
		/**
		 * Montando a lista de unidades com a soma total dos salarios
		 */
		gerarUnidadeSalario();
	}

	/**
	 * Listar todas as pessoas
	 * @return List<PessoaDTO>
	 */
	@Test
	public void findAllPessoa() {
		EasyMock.expect(pessoaService.findAll()).andReturn(listPessoa);
		EasyMock.replay(pessoaService);
		List<PessoaDTO> list = pessoaService.findAll();
		assertNotNull(list);
		EasyMock.verify(pessoaService);
	}
	
	/**
	 * Listar todas as pessoas usando como filtro o nome
	 * @param nome String - o nome da pessoa
	 * @return List<PessoaDTO> - Retorno em lista pois podem existir homonimos
	 */
	@Test
	public void findByNamePessoa() {
		EasyMock.expect(pessoaService.findByNome("Willans Firmo")).andReturn(listPessoa);
		EasyMock.replay(pessoaService);
		List<PessoaDTO> list = pessoaService.findByNome("Willans Firmo");
		assertNotNull(list);
		EasyMock.verify(pessoaService);
	}
	
	/**
	 * Criar pessoa
	 * @param PessoaDTO
	 * @return PessoaDTO
	 */
	@Test
	public void criarPessoa() {
		PessoaDTO dto = new PessoaDTO();
		dto.codigo = 12L;
		dto.nome = "Willans Firmo";
		dto.salario = 974.20;
		dto.unidade = new UnidadeDTO();
		dto.unidade.codigo = 465L;
		dto.unidade.nome = "Unidade de teste mockito";
		
		Unidade unidade = getUnidade();
		Pessoa pessoa = getPessoa();
		pessoa.setUnidade(unidade);
		
		EasyMock.expect(unidadeRepository.findOne(dto.unidade.codigo)).andReturn(unidade);
		EasyMock.replay(unidadeRepository);
		Unidade unidadeMock = unidadeRepository.findOne(dto.unidade.codigo);
		assertNotNull(unidadeMock);
		
		EasyMock.verify(unidadeRepository);
		
		EasyMock.expect(pessoaRepository.findOne(dto.codigo)).andReturn(null);
		EasyMock.replay(pessoaRepository);
		Pessoa pessoaMock = pessoaRepository.findOne(dto.codigo);
		assertNull(pessoaMock);
		
		EasyMock.verify(pessoaRepository);
		
		EasyMock.expect(pessoaService.create(dto)).andReturn(dto);
		EasyMock.replay(pessoaService);
		PessoaDTO pessoaDTO = pessoaService.create(dto);
		assertNotNull(pessoaDTO);
		
		EasyMock.verify(pessoaService);
	}
	
	@Test
	public void criarUnidade() {
		UnidadeDTO dto = new UnidadeDTO();
		dto.codigo = 465L;
		dto.nome = "Unidade de teste mockito";
		
		EasyMock.expect(unidadeRepository.findOne(dto.codigo)).andReturn(null);
		EasyMock.replay(unidadeRepository);
		Unidade unidadeMock = unidadeRepository.findOne(dto.codigo);
		assertNull(unidadeMock);
		
		EasyMock.verify(unidadeRepository);
		
		EasyMock.expect(unidadeService.create(dto)).andReturn(dto);
		EasyMock.replay(unidadeService);
		UnidadeDTO unidadeDTO = unidadeService.create(dto);
		assertNotNull(unidadeDTO);
		
		EasyMock.verify(unidadeService);
	}
	
	/**
	 * Listar todas as unidade e a soma dos salarios das pessoas vinculadas a ela
	 * @return List<RelatorioUnidadeQtdPessoaDTO>
	 */
	@Test
	public void findTotalSalarioPorUnidade() {
		EasyMock.expect(unidadeService.findAllUnidadesTotalSalario()).andReturn(listTotalSalarioPorUnidade);
		EasyMock.replay(unidadeService);
		List<RelatorioUnidadeTotalSalarioDTO> list = unidadeService.findAllUnidadesTotalSalario();
		assertNotNull(list);
		EasyMock.verify(unidadeService);
	}
	
	/**
	 * Listar todas as unidade e a quantodade de pessoas vinculadas a ela
	 * @return List<RelatorioUnidadeTotalSalarioDTO>
	 */
	@Test
	public void findRelatorioQtdPessoaPorUnidade() {
		EasyMock.expect(unidadeService.findAllUnidadesQtdPessoa()).andReturn(listQtdPessoaPorUnidade);
		EasyMock.replay(unidadeService);
		List<RelatorioUnidadeQtdPessoaDTO> list = unidadeService.findAllUnidadesQtdPessoa();
		assertNotNull(list);
		EasyMock.verify(unidadeService);
	}
	
	/**
	 * Montagem da lista de Pessoas
	 */
	private void gerarLista() {
		Pessoa pessoa = getPessoa();
		
		Unidade unidade = getUnidade();
		pessoa.setUnidade(unidade);
		
		listPessoa.add(new PessoaDTO(pessoa));
	}
	
	/**
	 * Monta uma pessoa
	 * @return Pessoa
	 */
	private Pessoa getPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo(12L);
		pessoa.setNome("Willans Firmo");
		pessoa.setSalario(974.20);
		
		return pessoa;
	}
	
	/**
	 * Monta uma Unidade
	 * @return Unidade
	 */
	private Unidade getUnidade() {
		Unidade unidade = new Unidade();
		unidade.setCodigo(465L);
		unidade.setNome("Unidade de teste mockito");
		
		return unidade;
	}
	
	/**
	 * Montagem da lista de unidades com quantidade de pessoas
	 */
	private void gerarUnidadeQtdPessoa() {
		RelatorioUnidadeQtdPessoaDTO relatiro = new RelatorioUnidadeQtdPessoaDTO();
		relatiro.nome = "Unidade Mock";
		relatiro.qtdPessoa = 3;
		
		listQtdPessoaPorUnidade.add(relatiro);
	}
	
	/**
	 * Montagem da lista de unidades com o total de salarios
	 */
	private void gerarUnidadeSalario() {
		RelatorioUnidadeTotalSalarioDTO relatorio = new RelatorioUnidadeTotalSalarioDTO();
		relatorio.nome = "Unidade Mock";
		relatorio.totalSalario = 895.10;
		
		listTotalSalarioPorUnidade.add(relatorio);
	}

}
