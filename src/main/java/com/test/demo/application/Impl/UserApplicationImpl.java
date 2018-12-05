package com.test.demo.application.Impl;

import com.test.demo.application.UserApplication;
import com.test.demo.domain.User;
import com.test.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserApplicationImpl implements UserApplication {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUserList() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserId(Long userId) throws Exception {
        return userRepository.getUserByUserId(userId);
    }

    @Override
    public Page<User> getUserListByUserName(String userName) throws Exception {
        return userRepository.getUserListByUserName(userName);
    }

    @Override
    public List<User> findUserListByUsernameAndUserPwd(String userName, String userPwd) throws Exception {
        return userRepository.findUserListByUsernameAndUserPwd( userName,  userPwd);
    }


    @Override
    public User modUserNameById(Long id, String username, String newname) throws Exception {
        return userRepository.modUserNameById(id,username,newname);
    }
}
