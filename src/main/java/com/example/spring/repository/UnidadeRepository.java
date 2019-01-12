package com.example.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.spring.dto.RelatorioUnidadeQtdPessoaDTO;
import com.example.spring.dto.RelatorioUnidadeTotalSalarioDTO;
import com.example.spring.model.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

	@Query(value = "SELECT DISTINCT(u.nome), COUNT(p.codigo) qtdPessoas "
			+ "FROM unidade u "
			+ "JOIN pessoa p "
			+ "WHERE u.codigo = p.unidade "
			+ "GROUP BY u.nome "
			+ "ORDER BY u.nome ASC", nativeQuery = true)
	List<RelatorioUnidadeQtdPessoaDTO> findUnidadeQtdPessoa();

	@Query(value = "SELECT DISTINCT(u.nome), SUM(p.salario) totalSalario "
			+ "FROM unidade u "
			+ "JOIN pessoa p "
			+ "WHERE u.codigo = p.unidade "
			+ "GROUP BY u.nome "
			+ "ORDER BY u.nome ASC", nativeQuery = true)
	List<RelatorioUnidadeTotalSalarioDTO> findUnidadeTotalSalario();

}
