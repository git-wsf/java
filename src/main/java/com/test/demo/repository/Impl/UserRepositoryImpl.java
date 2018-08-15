package com.test.demo.repository.Impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.test.demo.domain.QUser;
import com.test.demo.domain.User;
import com.test.demo.repository.UserRepository;
import com.test.demo.repository.dao.UserDao;
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
    public User save(User user)  {
        return userDao.save(user);

    }

    @Override
    public User getUserByUserId(Long userId) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from users where user_id = %s");
        String sql = sb.toString();
        sql = String.format(sql, userId);
        Query userQuery =  entityManager.createNativeQuery(sql,User.class);
        List users = userQuery.getResultList();
        return (User) users.get(0);
    }

    @Override
    public Page<User> getUserListByUserName(String userName)  {

        int pageNo=0;
        int pageSize=5;
        PageRequest pageRequest=new PageRequest(pageNo, pageSize);

        Specification<User> specification = (root, query, cb) -> {
            Predicate predicateUserName=cb.like(root.get("username").as(String.class), "%"+userName+"%");
            Predicate predicateUserPwd=cb.like(root.get("userpwd").as(String.class), "%"+userName+"%");
            return cb.or(predicateUserName,predicateUserPwd);
        };

        Page<User> users = userDao.findAll(specification,pageRequest);
        return users;
    }

    @Override
    public List<User> findUserListByUsernameAndUserPwd(String userName,String userPwd) throws Exception {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Predicate usernamePredicate = null;
        Predicate userPwdPredicate = null;
        if(!StringUtils.isEmpty(userName)){
            usernamePredicate = builder.equal(root.get("username"), userName);
        }
        if(!StringUtils.isEmpty(userPwd)){
            userPwdPredicate = builder.equal(root.get("userpwd"), userPwd);
        }
        query.where(builder.or(usernamePredicate, userPwdPredicate));
        return entityManager.createQuery(query.select(root)).getResultList();
    }

    @Override
    public List<User> findUserListByUserPwd(String userPwd) throws Exception {
        QUser user = QUser.user;
        JPAQuery<?> query = new JPAQuery<Void>(entityManager);
        JPAQuery<User> bob = query.select(user)
                .from(user)
                .where(user.userpwd.eq(userPwd))
                .fetchAll();
       return bob.fetchAll().fetch();

//        QUser user = QUser.User;
//        BooleanExpression customerHasBirthday = customer.birthday.eq(today);
//        BooleanExpression isLongTermCustomer = customer.createdAt.lt(today.minusYears(2));
    }
}
