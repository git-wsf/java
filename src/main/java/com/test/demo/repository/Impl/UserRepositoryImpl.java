package com.test.demo.repository.Impl;

import com.test.demo.domain.User;
import com.test.demo.repository.UserRepository;
import com.test.demo.repository.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserDao userDao;


    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public User save(User user) throws Exception {
        return userDao.save(user);

    }

    @Override
    public User getUserByUserId(Long userId) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("select * from users where user_id = %s");
        String sql = sb.toString();
        sql = String.format(sql, userId);
        Query userQuery =  entityManager.createNativeQuery(sql,User.class);
        List users = userQuery.getResultList();
        return (User) users.get(0);
    }

    @Override
    public Page<User> getUserListByUserName(String userName) throws Exception {

        int pageNo=0;
        int pageSize=5;
        PageRequest pageRequest=new PageRequest(pageNo, pageSize);

        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Predicate predicateUserName=cb.like(root.get("username").as(String.class), "%"+userName+"%");
                Predicate predicateUserPwd=cb.like(root.get("userpwd").as(String.class), "%"+userName+"%");
                //Predicate p3=cb.like(root.get("email").as(String.class), "%s%");
//              构建组合的Predicate示例：
                Predicate p = cb.or(predicateUserName,predicateUserPwd);

                return p;
            }
        };

        Page<User> users = userDao.findAll(specification,pageRequest);
        return users;
    }
}
