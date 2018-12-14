package com.yangliuxin.application;

import com.yangliuxin.vo.LoginUser;
import com.yangliuxin.vo.Token;

public interface TokenService {

	Token saveToken(LoginUser loginUser);

	void refresh(LoginUser loginUser);

	LoginUser getLoginUser(String token);

	boolean deleteToken(String token);

}
