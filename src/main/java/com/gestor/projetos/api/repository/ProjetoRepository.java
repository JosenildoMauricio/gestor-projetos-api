package com.gestor.projetos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestor.projetos.api.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
	
	

}
