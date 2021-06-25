package com.example.firstservice.controller;

import com.example.firstservice.service.FirstService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {
    private final Environment env;
    private final FirstService firstService;

    @Autowired
    public FirstServiceController(Environment env, FirstService firstService) {
        this.env = env;
        this.firstService = firstService;
    }

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in First Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", config test=" + env.getProperty("test.text");
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "Hello World in First Service.";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port={}", request.getServerPort());

        return String.format("Hi, there. This is a message from First Service on PORT %s"
                , env.getProperty("local.server.port"));
    }

    @GetMapping("/second")
    public ResponseEntity<String> getSecond() {
        String secondDto = firstService.secondCheck();
        return ResponseEntity.status(HttpStatus.OK).body(secondDto);
    }
}
