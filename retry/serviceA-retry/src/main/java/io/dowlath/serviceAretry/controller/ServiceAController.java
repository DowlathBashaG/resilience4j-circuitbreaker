package io.dowlath.serviceAretry.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/a")
public class ServiceAController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8081/";

    private static final String SERVICE_A = "ServiceA";

    @GetMapping
    @Retry(name = SERVICE_A)
    public String serviceA(){
        String url = BASE_URL + "b";
        return restTemplate.getForObject(url,String.class);
    }
    public String serviceAFallback(Exception e){
        return "This is a fallback method for Service A";
    }


}
