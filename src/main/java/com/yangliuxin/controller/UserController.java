package com.yangliuxin.controller;

import com.yangliuxin.annotation.LogAnnotation;
import com.yangliuxin.application.UserService;
import com.yangliuxin.domain.SysUser;
import com.yangliuxin.enums.WebCodeEnum;
import com.yangliuxin.page.PageTableHandler;
import com.yangliuxin.page.PageTableHandler.CountHandler;
import com.yangliuxin.page.PageTableHandler.ListHandler;
import com.yangliuxin.page.PageTableRequest;
import com.yangliuxin.page.PageTableResponse;
import com.yangliuxin.repository.UserRepository;
import com.yangliuxin.utils.UserUtil;
import com.yangliuxin.vo.ResultVo;
import com.yangliuxin.vo.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "用户")

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {


	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "保存用户")
	@PreAuthorize("hasAuthority('sys:user:add')")
	public SysUser saveUser(@RequestBody UserDto userDto) {
		SysUser u = userService.getUser(userDto.getUsername());
		if (u != null) {
			throw new IllegalArgumentException(userDto.getUsername() + "已存在");
		}

		return userService.saveUser(userDto);
	}

	@LogAnnotation
	@PutMapping
	@ApiOperation(value = "修改用户")
	@PreAuthorize("hasAuthority('sys:user:add')")
	public SysUser updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}

	@LogAnnotation
	@PutMapping(params = "headImgUrl")
	@ApiOperation(value = "修改头像")
	public void updateHeadImgUrl(String headImgUrl) {
		SysUser user = UserUtil.getLoginUser();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		userDto.setHeadImgUrl(headImgUrl);

		userService.updateUser(userDto);
		log.debug("{}修改了头像", user.getUsername());
	}

	@LogAnnotation
	@PutMapping("/{username}")
	@ApiOperation(value = "修改密码")
	@PreAuthorize("hasAuthority('sys:user:password')")
	public ResultVo<Boolean> changePassword(@PathVariable String username, String oldPassword, String newPassword) throws Exception {
		ResultVo<Boolean> resultVo = new ResultVo<>();
		resultVo.setCode(WebCodeEnum.SUCCESS.getValue());
		userService.changePassword(username, oldPassword, newPassword);
		return resultVo;
	}

	@GetMapping
	@ApiOperation(value = "用户列表")
	@PreAuthorize("hasAuthority('sys:user:query')")
	public PageTableResponse listUsers(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return userRepository.count(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<SysUser> list(PageTableRequest request) {
				List<SysUser> list = userRepository.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	@ApiOperation(value = "当前登录用户")
	@GetMapping("/current")
	public SysUser currentUser() {
		return UserUtil.getLoginUser();
	}

	@ApiOperation(value = "根据用户id获取用户")
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('sys:user:query')")
	public SysUser user(@PathVariable Long id) {
		return userRepository.getById(id);
	}


}
