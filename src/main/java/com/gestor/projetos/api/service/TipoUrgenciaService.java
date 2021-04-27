package com.gestor.projetos.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gestor.projetos.api.model.TipoUrgencia;
import com.gestor.projetos.api.repository.TipoUrgenciaRepository;

@Service
public class TipoUrgenciaService {

	@Autowired
	private TipoUrgenciaRepository tipoUrgenciaRepository;

	public TipoUrgencia atualizar(Long id, TipoUrgencia tipoUrgencia) {
		TipoUrgencia tipoUrgenciaSalva = tipoUrgenciaRepository.getOne(id);
		if (tipoUrgenciaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(tipoUrgencia, tipoUrgenciaSalva, "id");
		return tipoUrgenciaRepository.save(tipoUrgenciaSalva);
	}
	
}
