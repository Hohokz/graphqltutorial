package com.example.graphqltutorial.repository;

import com.example.graphqltutorial.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
