package com.decisionwanted.domain.configuration;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.decisionwanted.domain.repository")
@EnableTransactionManagement
public class Neo4jTestConfig {

	@Value("${neo4j.server.database.uri}")
	private String serverDatabaseUri;

	@Value("${neo4j.username}")
	private String username;

	@Value("${neo4j.password}")
	private String password;

	@Bean
	public Neo4jTransactionManager transactionManager() {
		return new Neo4jTransactionManager(sessionFactory());
	}

	@Bean
	public SessionFactory sessionFactory() {
		return new SessionFactory(configuration(), "com.decisionwanted.domain.model");
	}

	@Bean
	public org.neo4j.ogm.config.Configuration configuration() {

		// @formatter:off
		return new org.neo4j.ogm.config.Configuration.Builder()
				.autoIndex("assert")
				.credentials(username, password)
				.uri(serverDatabaseUri)
				.build();
		// @formatter:on

	}

}