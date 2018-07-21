package com.test.demo.Controller;

import com.test.demo.application.UserApplication;
import com.test.demo.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserApplication userApplication;

    @RequestMapping("test")
    public String test() throws Exception {

        List<User> userList = userApplication.getAllUserList();
        User user = new User();
        user.setUsername("yangliuxin");
        user.setUserpwd("chunfengshili");
        userApplication.save(user);
        System.out.println(userList);

        LOGGER.info("log writing!");
        return "test";
    }
}
