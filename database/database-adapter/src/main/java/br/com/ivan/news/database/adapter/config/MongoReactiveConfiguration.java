package br.com.ivan.news.database.adapter.config;

import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@EnableJpaRepositories
@EnableReactiveMongoRepositories
public class MongoReactiveConfiguration extends AbstractReactiveMongoConfiguration {


    private static final String DATABASE_NAME = "news";

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(MongoClients.create(), DATABASE_NAME);
    }


    @Override
    protected String getDatabaseName() {
        return DATABASE_NAME;
    }

}
