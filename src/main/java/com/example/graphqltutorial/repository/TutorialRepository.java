package com.example.graphqltutorial.repository;

import com.example.graphqltutorial.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

}
