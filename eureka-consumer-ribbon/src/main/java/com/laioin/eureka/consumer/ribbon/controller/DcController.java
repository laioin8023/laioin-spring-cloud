package com.laioin.eureka.consumer.ribbon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {

    private Logger LGR = LoggerFactory.getLogger(DcController.class);


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer")
    public String consumer() {
        String url = "http://eureka-client/dc";
        LGR.info("request url = [{}] . ", url);
        String data = restTemplate.getForObject(url, String.class);
        LGR.info("request url = [{}] .data = [{}]. ", url, data);
        return data;
    }
}
