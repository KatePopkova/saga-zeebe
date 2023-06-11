package com.saga.inventory.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableMongoRepositories("com.saga.inventory.repository")
public class MongoConfiguration {
}
