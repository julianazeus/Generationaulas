package com.generation.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAllCategoria(){
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findByIdCategoria(@PathVariable(value = "id") long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categoria>> findByDescricaoCategoria(@PathVariable(value = "nome") String nome){
		return new ResponseEntity<>(repository.findAllByNomeContainingIgnoreCase(nome), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria){
		return new ResponseEntity<>(repository.save(categoria), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria){
		return new ResponseEntity<>(repository.save(categoria), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable(value = "id") long id) {
		repository.deleteById(id);
	}
}
