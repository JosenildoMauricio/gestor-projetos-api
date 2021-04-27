package com.gestor.projetos.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gestor.projetos.api.config.UsuarioSistema;
import com.gestor.projetos.api.model.Pessoa;
import com.gestor.projetos.api.repository.PessoaRepository;

@Service
public class AppUserDetailService implements UserDetailsService {

	@Autowired private PessoaRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Pessoa> optional = repository.findByEmail(email);
		Pessoa usuario = optional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return new UsuarioSistema(usuario, getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Pessoa usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		// usuario.getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
		return authorities;
	}

}
