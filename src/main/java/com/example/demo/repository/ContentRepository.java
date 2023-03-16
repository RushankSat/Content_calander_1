package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Content;
import com.example.demo.model.Status;

@Repository
public interface ContentRepository extends ListCrudRepository<Content,Integer> {

	List<Content> findAllByTitleContains(String keyword);
	
	@Query( """
			SELECT * FROM Content
			WHERE status= :status
			""")
	List<Content> ListByStatus(@Param("status") Status status);
}
