package com.laioin.eureka.consumer.feign.controller;

import com.laioin.eureka.consumer.feign.service.IDcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController {
    @Autowired
    private IDcServer dcServer;

    @RequestMapping("/consumer")
    public String dc() {
        String data = dcServer.consumer();
        return data;
    }
}
