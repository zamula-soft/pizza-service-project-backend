package com.example.pizzaserviceproject;

import com.example.pizzaserviceproject.repository.CafeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppIntegrationTest {

    @Value(value = "${local.server.port}")
    private int port;
    @Autowired private TestRestTemplate restTemplate;
    @Autowired CafeRepository cafeRepository;

    @Test
    public void createCafeTest() throws Exception{

        cafeRepository.deleteAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(
          "{\"name\":\"Test cafe 1\", \"email\":\"test1@email.com\"}",
          headers
        );

        String body =  restTemplate.postForEntity(
                "http://localhost:" + port +"/cafe/add",
                request,
                String.class
        ).getBody();

        String expectedAnswer = "{\"id\":1,\"rating\":null,\"name\":\"Test cafe 1\",\"city\":null,\"country\":null," +
                "\"address\":null,\"email\":\"test1@email.com\",\"site\":null,\"facebook\":null,\"phone\":null," +
                "\"delivery\":null,\"description\":null,\"open_at\":null,\"close_at\":null}";

        assertEquals(body, expectedAnswer);

    }


}
