package com.example.firstservice.service;

import com.example.firstservice.client.SecondServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FirstServiceImpl implements FirstService {
    Environment env;

    SecondServiceClient secondServiceClient;
    CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    public FirstServiceImpl(Environment env,
                            SecondServiceClient secondServiceClient,
                            CircuitBreakerFactory circuitBreakerFactory) {
        this.env = env;
        this.secondServiceClient = secondServiceClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @Override
    public String secondCheck() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        String second = circuitBreaker.run(() ->
                        secondServiceClient.getSecond(), throwable -> "");
        log.info("After called second microservice");
        return second;
    }

}
