package com.test.demo.Controller;

import com.test.demo.application.UserApplication;
import com.test.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
public class TestController {
    //private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserApplication userApplication;

    @GetMapping("/test/{userName}")
    public String test(@PathVariable String userName) throws Exception {

        List<User> userList = userApplication.getAllUserList();
        User user = new User();
        user.setUsername(userName);
        user.setUserpwd("chunfengshili");
        userApplication.save(user);
        System.out.println(userList);

        log.info("log writing!");
        return "test";
    }


    @GetMapping("/get/{userId}")
    public User get(@PathVariable Long userId) throws Exception {

       return  userApplication.getUserByUserId(userId);
    }



    @GetMapping("/like/{userName}")
    public Page<User> get(@PathVariable @Valid  @NotNull String userName) throws Exception {

        return  userApplication.getUserListByUserName(userName);
    }




}
