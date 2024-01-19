package com.example.graphqltutorial.resolver;

import com.example.graphqltutorial.model.Author;
import com.example.graphqltutorial.model.Tutorial;
import com.example.graphqltutorial.repository.AuthorRepository;
import com.example.graphqltutorial.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Component
public class Query implements GraphQLQueryResolver {
    private AuthorRepository authorRepository;
    private TutorialRepository tutorialRepository;

    GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long")
            .aliasedScalar(ExtendedScalars.GraphQLLong)
            .build();

    @Autowired
    public Query(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
        this.authorRepository = authorRepository;
        this.tutorialRepository = tutorialRepository;
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Iterable<Tutorial> findAllTutorials() {
        return tutorialRepository.findAll();
    }

    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Tutorial findTutorialById(Long id) {
        return tutorialRepository.findById(id).orElse(null);
    }

    public long countAuthors() {
        return authorRepository.count();
    }

    public long countTutorials() {
        return tutorialRepository.count();
    }

}
