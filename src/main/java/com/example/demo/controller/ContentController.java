package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Content;
import com.example.demo.model.Status;
import com.example.demo.repository.ContentRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/content")
public class ContentController {
	
	
	//ContentCollectionRespository repo;
	
	@Autowired
	ContentRepository repo;
	
	@GetMapping("")
	
	public List<Content>findAll(){
		return repo.findAll();
	}
		
		
	@GetMapping("{id}")	
	
	public Content findById(@PathVariable int id ) {
		return repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!"));
	}
		
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody Content content) {
		
		 
		 repo.save(content);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Content content, @PathVariable int id ) {
		if(!repo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!");
		}
		 repo.save(content);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		repo.deleteById(id);
	}
	
	@GetMapping("/filter/{Keyword}")
	
	public List<Content> findByTitle(@PathVariable String Keyword) 
	{
		return repo.findAllByTitleContains(Keyword);
	}

	
	@GetMapping("/filter/status/{status}")
	
	public List<Content> ListByStatus(@PathVariable Status status) 
	{
		return repo.ListByStatus(status);
	}
}
