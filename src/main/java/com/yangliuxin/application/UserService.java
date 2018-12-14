package com.yangliuxin.application;

import com.yangliuxin.domain.SysUser;
import com.yangliuxin.vo.UserDto;

public interface UserService {

	SysUser saveUser(UserDto userDto);

	SysUser updateUser(UserDto userDto);

	SysUser getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

}
