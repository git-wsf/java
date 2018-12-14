package com.yangliuxin.repository;

import com.yangliuxin.domain.TokenModel;

public interface TokenRepository {

    int save(TokenModel model);

    TokenModel getById(String id);

    int update(TokenModel model);

    int delete(String id);
}
