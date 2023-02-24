package com.example;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("{schoolName}/students")
public class SchoolServiceController {

    private final RestTemplate restTemplate;

    public SchoolServiceController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getStudents(@PathVariable String schoolName) {
        return restTemplate.exchange(
                "http://student-service/{schoolName}/students",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                },
                schoolName
        )
        .getBody();
    }

}
