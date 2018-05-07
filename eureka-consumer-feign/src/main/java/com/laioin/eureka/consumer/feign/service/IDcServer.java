package com.laioin.eureka.consumer.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-client")
public interface IDcServer {

    @GetMapping("/dc")
    String consumer();
}
