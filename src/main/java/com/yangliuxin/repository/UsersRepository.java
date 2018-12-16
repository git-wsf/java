package com.yangliuxin.repository;

import com.yangliuxin.domain.Users;

public interface UsersRepository {

    Users save(Users user);

    Users getById(Long id);

    Users getUserByOpenId(String openId);

}
