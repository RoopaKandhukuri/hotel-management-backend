package com.hotelmanagement.hotelmanagementbackend;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    private static final String CONNECTION_STRING =
        "mongodb+srv://kroopa07012002_db_user:rmNFhWjOPNDUuK3C@cluster0.qro8gd8.mongodb.net/hotel_management_db?retryWrites=true&w=majority";

    @Bean
    @Primary
    public MongoClient mongoClient() {
        return MongoClients.create(CONNECTION_STRING);
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "hotel_management_db");
    }
}