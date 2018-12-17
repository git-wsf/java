package com.yangliuxin;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
public class TestController {


    @RequestMapping("test")
    public String test(){
        log.info("log writing!");
        return "test";
    }
}
