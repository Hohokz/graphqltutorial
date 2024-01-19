package com.example.graphqltutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphqltutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqltutorialApplication.class, args);
	}
	@Bean
	public graphql.schema.GraphQLScalarType extendedScalarLong() {
		return graphql.scalars.ExtendedScalars.GraphQLLong;
	}

}
