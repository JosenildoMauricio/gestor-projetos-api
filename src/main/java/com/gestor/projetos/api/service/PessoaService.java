package com.gestor.projetos.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestor.projetos.api.model.Pessoa;
import com.gestor.projetos.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaRepository.getOne(id);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		return pessoaRepository.save(pessoaSalva);
	}
	
	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(id);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}
	
	public Pessoa criptografarSenha(Pessoa pessoa) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senha = pessoa.getSenha();
		pessoa.setSenha(encoder.encode(senha));
		
		return pessoa;
	}
	
	private Pessoa buscarPessoaPeloCodigo(Long id) {
		Pessoa pessoaSalva = pessoaRepository.getOne(id);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
	
}
