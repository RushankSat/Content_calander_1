package com.example.demo.config;


import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Content;
import com.example.demo.repository.ContentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Dataloader implements CommandLineRunner {
	@Autowired
	ContentRepository repo;
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void run(String... args) throws Exception {
	
		if(repo.count()==0) {
		try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")){
		repo.saveAll(objectMapper.readValue(inputStream,new TypeReference<List<Content>>() {}));
			}
		}
	}

}
