package com.gestor.projetos.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gestor.projetos.api.model.Projeto;
import com.gestor.projetos.api.repository.ProjetoRepository;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository projetoRepository;

	public Projeto atualizar(Long id, Projeto projeto) {
		Projeto projetoSalvo = projetoRepository.getOne(id);
		if (projetoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(projeto, projetoSalvo, "id");
		return projetoRepository.save(projetoSalvo);
	}
	
}
