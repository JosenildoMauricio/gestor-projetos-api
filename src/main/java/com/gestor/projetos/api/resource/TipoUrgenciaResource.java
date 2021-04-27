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
import com.gestor.projetos.api.model.TipoUrgencia;
import com.gestor.projetos.api.repository.TipoUrgenciaRepository;
import com.gestor.projetos.api.service.TipoUrgenciaService;

@RestController
@RequestMapping("/tipos_urgencias")
public class TipoUrgenciaResource {
	
	@Autowired
	private TipoUrgenciaRepository tipoUrgenciaRepository; 
	
	@Autowired
	private TipoUrgenciaService tipoUrgenciaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<TipoUrgencia> listar() {
		return tipoUrgenciaRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<TipoUrgencia> inserir(@Valid @RequestBody TipoUrgencia tipoUrgencia, HttpServletResponse response) {
		TipoUrgencia tipoUrgenciaSalva = tipoUrgenciaRepository.save(tipoUrgencia);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoUrgenciaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoUrgenciaSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<TipoUrgencia>> buscaPeloId(@PathVariable Long id) {
		
		Optional<TipoUrgencia> tipoUrgencia = tipoUrgenciaRepository.findById(id);
		
		return !tipoUrgencia.isEmpty() ? ResponseEntity.ok(tipoUrgencia) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		tipoUrgenciaRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TipoUrgencia> atualizar(@PathVariable Long id, @Valid @RequestBody TipoUrgencia tipoUrgencia) {
		TipoUrgencia tipoUrgenciaSalva = tipoUrgenciaService.atualizar(id, tipoUrgencia);
		return ResponseEntity.ok(tipoUrgenciaSalva);
	}
	

}
