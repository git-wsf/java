package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.TokenModel;
import com.yangliuxin.repository.TokenRepository;
import org.springframework.stereotype.Repository;


@Repository
public class TokenRepositoryImpl implements TokenRepository {
    @Override
    public int save(TokenModel model) {
        return 0;
    }

    @Override
    public TokenModel getById(String id) {
        return null;
    }

    @Override
    public int update(TokenModel model) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }
}
