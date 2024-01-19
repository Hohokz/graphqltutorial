package com.example.graphqltutorial.resolver;

import com.example.graphqltutorial.model.Author;
import com.example.graphqltutorial.model.Tutorial;
import com.example.graphqltutorial.repository.AuthorRepository;
import com.example.graphqltutorial.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private AuthorRepository authorRepository;
    private TutorialRepository tutorialRepository;

    @Autowired
    public Mutation(AuthorRepository authorRepository, TutorialRepository tutorialRepository) {
        this.authorRepository = authorRepository;
        this.tutorialRepository = tutorialRepository;
    }

    public Author createAuthor(String name, Integer age) {
        Author author = new Author();
        author.setName(name);
        author.setAge(age);

        authorRepository.save(author);

        return author;
    }

    public Tutorial createTutorial(String title, String description, Long authorId) {
        Tutorial tutorial = new Tutorial();
        tutorial.setAuthor(new Author(authorId));
        tutorial.setTitle(title);
        tutorial.setDescription(description);

        tutorialRepository.save(tutorial);

        return tutorial;
    }

    public boolean deleteTutorial(Long id) {
        Optional<Tutorial> optTutorial = tutorialRepository.findById(id);

        if (optTutorial.isPresent()) {
            tutorialRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteAuthor(Long id) {
        Optional<Author> optAuthor = authorRepository.findById(id);
        if (optAuthor.isPresent()) {
            authorRepository.deleteById(id);
        }
        return false;
    }

    public Author updateAuthor(Long id, String name, Integer age) throws EntityNotFoundException {
        Optional<Author> optAuthor = authorRepository.findById(id);

        if (optAuthor.isPresent()) {
            Author author = optAuthor.get();

            if (name != null)
                author.setName(name);
            if (age != null)
                author.setAge(age);

            authorRepository.save(author);
            return author;
        }
        throw new EntityNotFoundException("Not found Tutorial to update!");

    }

    public Tutorial updateTutorial(Long id, String title, String description) throws EntityNotFoundException {
        Optional<Tutorial> optTutorial = tutorialRepository.findById(id);

        if (optTutorial.isPresent()) {
            Tutorial tutorial = optTutorial.get();

            if (title != null)
                tutorial.setTitle(title);
            if (description != null)
                tutorial.setDescription(description);

            tutorialRepository.save(tutorial);
            return tutorial;
        }

        throw new EntityNotFoundException("Not found Tutorial to update!");
    }

}
