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

    @GetMapping("/put/{userName}/{userPwd}")
    public String test(@PathVariable @Valid @NotNull String userName,@PathVariable @Valid @NotNull String userPwd) throws Exception {

        List<User> userList = userApplication.getAllUserList();
        User user = new User();
        user.setUsername(userName);
        user.setUserpwd(userPwd);
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
    @GetMapping("/e/{userName}/{userPwd}")
    public List<User> get(@PathVariable @Valid  @NotNull String userName,@PathVariable @Valid  @NotNull String userPwd) throws Exception {

        return  userApplication.findUserListByUsernameAndUserPwd(userName,userPwd);
    }


    @GetMapping("p/{userPwd}")
    public List<User> getByUserPwd(@PathVariable @Valid  @NotNull String userPwd) throws Exception {

        return  userApplication.findUserListByUserPwd(userPwd);
    }


    @GetMapping("/m/{id}/{username}/{newname}")
    public User modUserNameById(@PathVariable @Valid  @NotNull Long id,@PathVariable @Valid  @NotNull String username,@PathVariable @Valid  @NotNull String newname) throws Exception {

        return  userApplication.modUserNameById(id,username,newname);
    }







}
