package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Content;
import com.example.demo.model.Status;
import com.example.demo.model.Type;
import jakarta.annotation.PostConstruct;

@Repository
public class ContentCollectionRespository {
		private final List<Content> ContentList = new ArrayList<>();
		
		public ContentCollectionRespository() {
		}
			
			public List<Content> findAll(){
				return ContentList;
			}
			
			public Optional<Content> findById(Integer id){
				return ContentList.stream().filter(c->c.id().equals(id)).findFirst();
			}
			
			public void save(Content content) {
				ContentList.removeIf(c->c.id().equals(content.id()));
				ContentList.add(content);
			}
			
			public Boolean existById(int id) {
				return ContentList.stream().filter(c->c.id().equals(id)).count()==1;
			}
			
			public void delete(int id) {
				ContentList.removeIf(c->c.id().equals(id));
			}
			
			@PostConstruct
			private void init()
			{
				Content c = new Content(1,
				"My First Blog post",
				"my first blog post",
				Status.IDEA,
				Type.ARTICLE,
				LocalDateTime.now(),
				null,
				"");
				
				Content c1 = new Content(2,
						"My Second Blog post",
						"my second blog post",
						Status.IDEA,
						Type.ARTICLE,
						LocalDateTime.now(),
						null,
						"");
				ContentList.add(c);
				ContentList.add(c1);
			}
		
		
}
