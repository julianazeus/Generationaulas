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

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAllProduto(){
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findByIdProduto(@PathVariable(value = "id") long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> findByNomeProduto(@PathVariable(value = "nome") String nome){
		return new ResponseEntity<>(repository.findAllByNomeContainingIgnoreCase(nome), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
		return new ResponseEntity<>(repository.save(produto), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto){
		return new ResponseEntity<>(repository.save(produto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable(value = "id") long id) {
		repository.deleteById(id);
	}
 }
