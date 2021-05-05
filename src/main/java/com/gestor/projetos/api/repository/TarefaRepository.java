package com.gestor.projetos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestor.projetos.api.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{
	
	@Query("from Tarefa where projeto.id = :idProjeto")
	List<Tarefa> listaTarefaPorIdProjeto(Long idProjeto);

}
