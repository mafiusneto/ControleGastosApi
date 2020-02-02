package com.rneto.controlegastosapi.resource;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rneto.controlegastosapi.event.RecursoCriadoEvent;
import com.rneto.controlegastosapi.model.Grupo;
import com.rneto.controlegastosapi.repository.GrupoRepository;

@RestController
@RequestMapping("/grupos")
public class GrupoResource {
	
	@Autowired
	private GrupoRepository grupoRep;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Grupo> listar() {
		return grupoRep.findAll();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Grupo>> buscarPeloCodigo(@PathVariable Long codigo) { 
		Optional<Grupo> grupo = grupoRep.findById(codigo);	
		if (grupo.isPresent()) {
			return new ResponseEntity<>(grupo, HttpStatus.OK); // return ResponseEntity<Optional<Categoria>>
		} 
		return ResponseEntity.notFound().build();
	}
	@PostMapping
	// @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
	public ResponseEntity<Grupo> criar(@Valid @RequestBody Grupo grupo, HttpServletResponse response) {
		Grupo grupoSalva = grupoRep.save(grupo);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, grupoSalva.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(grupoSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Deletar(@PathVariable Long codigo) {
		grupoRep.deleteById(codigo);		
	}
}
