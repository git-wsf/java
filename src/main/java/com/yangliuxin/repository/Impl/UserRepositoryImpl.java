package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.User;
import com.yangliuxin.domain.UserVersion;
import com.yangliuxin.repository.UserRepository;
import com.yangliuxin.repository.dao.UserDao;
import com.yangliuxin.repository.dao.UserVersionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserDao userDao;


    @Autowired
    private UserVersionDao userVersionDao;


    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public User save(User user) {
        return userDao.save(user);

    }

    @Override
    public User getUserByUserId(Long userId) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from users where id = %s");
        String sql = sb.toString();
        sql = String.format(sql, userId);
        Query userQuery = entityManager.createNativeQuery(sql, User.class);
        List users = userQuery.getResultList();
        return (User) users.get(0);
    }

    @Override
    public Page<User> getUserListByUserName(String userName) {

        int pageNo = 0;
        int pageSize = 5;
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);

        Specification<User> specification = (root, query, cb) -> {
            Predicate predicateUserName = cb.like(root.get("username").as(String.class), "%" + userName + "%");
            Predicate predicateUserPwd = cb.like(root.get("userpwd").as(String.class), "%" + userName + "%");
            return cb.or(predicateUserName, predicateUserPwd);
        };

        Page<User> users = userDao.findAll(specification, pageRequest);
        return users;
    }

    @Override
    public List<User> findUserListByUsernameAndUserPwd(String userName, String userPwd) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Predicate usernamePredicate = null;
        Predicate userPwdPredicate = null;
        if (!StringUtils.isEmpty(userName)) {
            usernamePredicate = builder.equal(root.get("username"), userName);
        }
        if (!StringUtils.isEmpty(userPwd)) {
            userPwdPredicate = builder.equal(root.get("userpwd"), userPwd);
        }
        query.where(builder.or(usernamePredicate, userPwdPredicate));
        return entityManager.createQuery(query.select(root)).getResultList();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public User modUserNameById(Long id, String username, String newname) throws Exception {


        User user = userDao.getOne(id);
        UserVersion userVersion = new UserVersion(user);
        user.setUsername(newname);
        userDao.save(user);


        userVersionDao.save(userVersion);
        return user;
    }
}