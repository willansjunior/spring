package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.model.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

}
