package com.yangliuxin.application.Impl;

import com.yangliuxin.application.UserService;
import com.yangliuxin.domain.Permission;
import com.yangliuxin.domain.SysUser;
import com.yangliuxin.enums.UserStatusEnum;
import com.yangliuxin.repository.PermissionRepository;
import com.yangliuxin.vo.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = userService.getUser(username);
		if (sysUser == null) {
			throw new AuthenticationCredentialsNotFoundException("用户名不存在");
		} else if (sysUser.getStatus() == UserStatusEnum.LOCKED.getValue()) {
			throw new LockedException("用户被锁定,请联系管理员");
		} else if (sysUser.getStatus() == UserStatusEnum.DISABLED.getValue()) {
			throw new DisabledException("用户已作废");
		}

		LoginUser loginUser = new LoginUser();
		BeanUtils.copyProperties(sysUser, loginUser);

		List<Permission> permissions = permissionRepository.listByUserId(sysUser.getId());
		loginUser.setPermissions(permissions);

		return loginUser;
	}

}
