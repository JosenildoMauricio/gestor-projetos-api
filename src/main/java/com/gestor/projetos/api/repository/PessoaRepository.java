package com.gestor.projetos.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestor.projetos.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	Optional<Pessoa> findByEmail(String email);

}
