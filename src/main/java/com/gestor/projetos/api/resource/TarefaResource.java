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
import com.gestor.projetos.api.model.Tarefa;
import com.gestor.projetos.api.repository.TarefaRepository;
import com.gestor.projetos.api.service.TarefaService;

@RestController
@RequestMapping("/tarefas")
public class TarefaResource {
	
	@Autowired
	private TarefaRepository tarefaRepository; 
	
	@Autowired
	private TarefaService tarefaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Tarefa> listar() {
		return tarefaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Tarefa> inserir(@Valid @RequestBody Tarefa tarefa, HttpServletResponse response) {
		Tarefa tarefaSalva = tarefaRepository.save(tarefa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tarefaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Tarefa>> buscaPeloId(@PathVariable Long id) {
		
		Optional<Tarefa> tarefa = tarefaRepository.findById(id);
		
		return !tarefa.isEmpty() ? ResponseEntity.ok(tarefa) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		tarefaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
		Tarefa tarefaSalva = tarefaService.atualizar(id, tarefa);
		return ResponseEntity.ok(tarefaSalva);
	}
	

}
