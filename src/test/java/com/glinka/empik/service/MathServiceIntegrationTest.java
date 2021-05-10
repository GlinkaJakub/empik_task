package com.glinka.empik.service;

import com.glinka.empik.dto.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathServiceIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    IMathService mathService;

    @Autowired
    public MathServiceIntegrationTest(IMathService mathService) {
        this.mathService = mathService;
    }

    @Test
    public void testAddSimpleNumber(){
        HttpEntity<String> entity = new HttpEntity<>("1;2;3", new HttpHeaders());
        ResponseEntity<Result> response = restTemplate.postForEntity(
                createURLWithPort("/math/add"),
                entity,
                Result.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(6, response.getBody().getSum());
    }

    @Test
    public void testAddWithEmptyStringNumbers(){
        HttpEntity<String> entity = new HttpEntity<>("", new HttpHeaders());
        ResponseEntity<Result> response = restTemplate.postForEntity(
                createURLWithPort("/math/add"),
                entity,
                Result.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().getSum());
    }

    @Test
    public void testAddWithMultiDelimiters(){
        HttpEntity<String> entity = new HttpEntity<>("//[***][..][;]\\n1***2;7..3..2***300", new HttpHeaders());
        ResponseEntity<Result> response = restTemplate.postForEntity(
                createURLWithPort("/math/add"),
                entity,
                Result.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(315, response.getBody().getSum());
    }

    @Test
    public void testAddWrongStringNumbers(){
        HttpEntity<String> entity = new HttpEntity<>("//[***][..][;]\\n;empik", new HttpHeaders());
        ResponseEntity<Result> response = restTemplate.postForEntity(
                createURLWithPort("/math/add"),
                entity,
                Result.class
        );
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
