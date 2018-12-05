package com.test.demo.repository;

import com.test.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository  {

    List<User> findAll() throws Exception;

    User save(User user) throws Exception;

    User getUserByUserId(Long userId) throws Exception;

    Page<User> getUserListByUserName(String userName) throws Exception;

    List<User> findUserListByUsernameAndUserPwd(String userName,String userPwd) throws Exception;

    User modUserNameById(Long id,String username,String newname) throws Exception;


}
