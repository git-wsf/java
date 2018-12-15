package com.yangliuxin.application.Impl;

import com.yangliuxin.application.UserService;
import com.yangliuxin.domain.SysUser;
import com.yangliuxin.enums.UserStatusEnum;
import com.yangliuxin.repository.UserRepository;
import com.yangliuxin.vo.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public SysUser saveUser(UserDto userDto) {
		SysUser user = userDto;
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setStatus(UserStatusEnum.VALID.getValue());
		userRepository.save(user);
		saveUserRoles(user.getId(), userDto.getRoleIds());

		log.debug("新增用户{}", user.getUsername());
		return user;
	}

	private void saveUserRoles(Long userId, List<Long> roleIds) {
		if (roleIds != null) {
			userRepository.deleteUserRole(userId);
			if (!CollectionUtils.isEmpty(roleIds)) {
				userRepository.saveUserRoles(userId, roleIds);
			}
		}
	}

	@Override
	public SysUser getUser(String username) {
		return userRepository.getUser(username);
	}

	@Override
	public void changePassword(String username, String oldPassword, String newPassword) {
		SysUser u = userRepository.getUser(username);
		if (u == null) {
			throw new IllegalArgumentException("用户不存在");
		}

		if (!passwordEncoder.matches(oldPassword, u.getPassword())) {
			throw new IllegalArgumentException("旧密码错误");
		}

		userRepository.changePassword(u.getId(), passwordEncoder.encode(newPassword));

		log.debug("修改{}的密码", username);
	}

	@Override
	@Transactional
	public SysUser updateUser(UserDto userDto) {
		userRepository.update(userDto);
		saveUserRoles(userDto.getId(), userDto.getRoleIds());

		return userDto;
	}

}
