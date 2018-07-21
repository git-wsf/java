package com.test.demo.application;

import com.test.demo.domain.User;

import java.util.List;

public interface UserApplication {


    List<User> getAllUserList() throws Exception;

    User save(User user) throws Exception;
}
