package com.gestor.projetos.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.gestor.projetos.api.event.RecursoCriadoEvent;
import com.gestor.projetos.api.model.Pessoa;
import com.gestor.projetos.api.repository.PessoaRepository;
import com.gestor.projetos.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository; 
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> inserir(@RequestBody Pessoa pessoa, HttpServletResponse response) {
		
		Pessoa pessoaSalva = pessoaRepository.save(pessoaService.criptografarSenha(pessoa));
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoa>> buscaPeloId(@PathVariable Long id) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		
		return !pessoa.isEmpty() ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buscar-por-email/{email}")
	public ResponseEntity<Optional<Pessoa>> buscaPeloEmail(@PathVariable String email) {
		
		Optional<Pessoa> pessoa = pessoaRepository.findByEmail(email);
		
		return !pessoa.isEmpty() ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizar(id, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(id, ativo);
	}
	

}
