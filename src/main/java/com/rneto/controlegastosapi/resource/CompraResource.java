package com.rneto.controlegastosapi.resource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import static java.time.temporal.TemporalAdjusters.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rneto.controlegastosapi.event.RecursoCriadoEvent;
import com.rneto.controlegastosapi.model.Compra;
import com.rneto.controlegastosapi.repository.CompraRepository;

@RestController
@RequestMapping("/compras")
public class CompraResource {

	@Autowired
	private CompraRepository compraRep;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Compra> listar() {
		return compraRep.findAll();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Compra>> buscarPeloCodigo(@PathVariable Long codigo) { 
		System.out.println("buscarPeloCodigo");
		Optional<Compra> compra = compraRep.findById(codigo);	
		if (compra.isPresent()) {
			return new ResponseEntity<>(compra, HttpStatus.OK); // return ResponseEntity<Optional<Categoria>>
		} 
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/venc/{data}")
	public ResponseEntity<List<Compra>> buscarPelaData(@PathVariable String data) {
		System.out.println("buscarPelaData");
		Compra compra = new Compra();
		compra.setDataVencimento(LocalDate.parse(data));
		System.out.println(compra);
		Example<Compra> example = Example.of(compra); 
		List<Compra> compras = compraRep.findAll(example);	
		return new ResponseEntity<>(compras, HttpStatus.OK);
	}
	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<List<Compra>> buscarPelaData2(@PathVariable Integer ano, @PathVariable Integer mes) {
		LocalDate data1 = LocalDate.of(ano, mes, 1); 
		LocalDate data2 = data1.with(lastDayOfMonth());
		List<Compra> compras = compraRep.findByDataVencimentoBetween(data1, data2);
		return new ResponseEntity<>(compras, HttpStatus.OK);
	}
		
	@PostMapping // @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
	public ResponseEntity<Compra> Salvar(@Valid @RequestBody Compra compra, HttpServletResponse response) {
		Compra compraSalva = compraRep.save(compra);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, compraSalva.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(compraSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Deletar(@PathVariable Long codigo) {
		compraRep.deleteById(codigo);		
	}
}
