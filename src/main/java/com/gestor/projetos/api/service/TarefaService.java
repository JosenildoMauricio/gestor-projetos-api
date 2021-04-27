package com.gestor.projetos.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gestor.projetos.api.model.Tarefa;
import com.gestor.projetos.api.repository.TarefaRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	public Tarefa atualizar(Long id, Tarefa tarefa) {
		Tarefa tarefaSalva = tarefaRepository.getOne(id);
		if (tarefaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(tarefa, tarefaSalva, "id");
		return tarefaRepository.save(tarefaSalva);
	}
	
}
