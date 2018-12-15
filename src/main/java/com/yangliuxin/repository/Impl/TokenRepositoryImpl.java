package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.TokenModel;
import com.yangliuxin.repository.TokenRepository;
import com.yangliuxin.repository.dao.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TokenRepositoryImpl implements TokenRepository {

    @Autowired
    private TokenDao tokenDao;

    @Override
    public int save(TokenModel model) {
        tokenDao.save(model);
        return 1;
    }

    @Override
    public TokenModel getById(String id) {
        return tokenDao.getOne(Long.parseLong(id));
    }

    @Override
    public int update(TokenModel model) {
        tokenDao.save(model);
        return 1;
    }

    @Override
    public int delete(String id) {
        tokenDao.delete(Long.parseLong(id));
        return 1;
    }
}
