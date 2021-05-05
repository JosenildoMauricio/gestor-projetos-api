package com.gestor.projetos.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestor.projetos.api.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
	
	@Query("from Projeto where pessoa.id = :idPessoa")
	List<Projeto> listaProjetosPorIdPessoa(Long idPessoa);

}
