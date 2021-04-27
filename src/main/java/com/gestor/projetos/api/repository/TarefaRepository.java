package com.gestor.projetos.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestor.projetos.api.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
	
	

}
