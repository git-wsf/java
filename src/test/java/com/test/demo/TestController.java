package com.test.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

public class TestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);


    @RequestMapping("test")
    public String test(){
        LOGGER.info("log writing!");

        return "test";
    }
}
