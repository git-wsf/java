package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.SysUser;
import com.yangliuxin.repository.UserRepository;
import com.yangliuxin.repository.dao.UserDao;
import jdk.nashorn.internal.runtime.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;


@Repository
public class UserRepositoryBaseImpl implements UserRepository {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EntityManager entityManager;


    private

    @Override
    public int save(SysUser user) {
        if(userDao.save(user).equals(true)) return 1;
        return 0;
    }

    @Override
    public SysUser getById(Long id) {
        return userDao.getOne(id);
    }

    @Override
    public SysUser getUser(String username) {
        return userDao.getSysUserByUsername(username);
    }

    @Override
    public int changePassword(Long id, String password) {
        SysUser user = userDao.findOne(id);
        user.setPassword(password);
        userDao.save(user);
        return 1;
    }

    @Override
    public Integer count(Map<String, Object> params) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> query = builder.createQuery(User.class);
//        Root<User> root = query.from(User.class);
//        Predicate usernamePredicate = null;
//        Predicate userPwdPredicate = null;
//        if (!StringUtils.isEmpty(userName)) {
//            usernamePredicate = builder.equal(root.get("username"), userName);
//        }
//        if (!StringUtils.isEmpty(userPwd)) {
//            userPwdPredicate = builder.equal(root.get("userpwd"), userPwd);
//        }
//        query.where(builder.or(usernamePredicate, userPwdPredicate));
//        return entityManager.createQuery(query.select(root)).getResultList();

//        int pageNo = 0;
//        int pageSize = 5;
//        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
//
//        Specification<User> specification = (root, query, cb) -> {
//            Predicate predicateUserName = cb.like(root.get("username").as(String.class), "%" + userName + "%");
//            Predicate predicateUserPwd = cb.like(root.get("userpwd").as(String.class), "%" + userName + "%");
//            return cb.or(predicateUserName, predicateUserPwd);
//        };
//
//        Page<User> users = userDao.findAll(specification, pageRequest);
//        return users;


//        StringBuilder sb = new StringBuilder();
//        sb.append("select * from users where id = %s");
//        String sql = sb.toString();
//        sql = String.format(sql, userId);
//        Query userQuery = entityManager.createNativeQuery(sql, User.class);
//        List users = userQuery.getResultList();
//        return (User) users.get(0);

        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from sys_user where 1 ");
        getSqlByParamMap(params, sb);
        String sql = sb.toString();
        Query userQuery = entityManager.createNativeQuery(sql, SysUser.class);
        return (Integer)userQuery.getSingleResult();
    }

    @Override
    public List<SysUser> list(Map<String, Object> params, Integer offset, Integer limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from sys_user where 1 ");
        getSqlByParamMap(params, sb);
        String sql = sb.toString();
        Query userQuery = entityManager.createNativeQuery(sql, SysUser.class);
        return userQuery.getResultList();
    }

    private void getSqlByParamMap(Map<String, Object> params, StringBuilder sb) {
        for (String key : params.keySet()) {
            if (!StringUtils.isEmpty(params.get(key))) {
                if(key.equals("username") || key.equals("nickname")){
                    sb.append("and " + key + " like '%"+params.get(key)+"%'");
                }
                if(key.equals("status")){
                    sb.append("and status = "+params.get(key)+"");
                }

            }
        }
    }

    @Override
    public int deleteUserRole(Long userId) {
        return 0;
    }

    @Override
    public int saveUserRoles(Long userId, List<Long> roleIds) {
        return 0;
    }

    @Override
    public int update(SysUser user) {
        return 0;
    }
}
