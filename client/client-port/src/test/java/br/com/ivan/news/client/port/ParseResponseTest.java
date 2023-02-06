package br.com.ivan.news.client.port;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParseResponseTest {

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final ClassPathResource classPathResource = new ClassPathResource("./response.json");

    @Test
    void parseResponseTest() throws IOException {
        String response = Files.readString(Paths.get(classPathResource.getFile().getAbsolutePath()));
        HeadlineResponse headline = mapper.readValue(response, HeadlineResponse.class);
        headline.getArticles().forEach(Assertions::assertNotNull);
        System.out.println(headline.getArticles());
    }
}
