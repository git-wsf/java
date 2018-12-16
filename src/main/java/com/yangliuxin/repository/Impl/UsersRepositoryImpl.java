package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Users;
import com.yangliuxin.repository.UsersRepository;
import com.yangliuxin.repository.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
public class UsersRepositoryImpl implements UsersRepository {


    @Autowired
    private EntityManager entityManager;


    @Autowired
    private UsersDao usersDao;


    @Override
    public Users save(Users user) {
        return usersDao.save(user);
    }

    @Override
    public Users getById(Long id) {
        return usersDao.getOne(id);
    }

    @Override
    public Users getUserByOpenId(String openId) {
        return usersDao.findByOpenId(openId);
    }
}
