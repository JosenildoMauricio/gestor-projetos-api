package com.gestor.projetos.api.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.gestor.projetos.api.model.Pessoa;

public class UsuarioSistema extends User {
	
	private Pessoa usuario;

	public UsuarioSistema(Pessoa usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	private static final long serialVersionUID = 1L;

}
