package com.example.firstservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="my-second-service")
public interface SecondServiceClient {

    @GetMapping("/second-service/check")
    String getSecond();

}
