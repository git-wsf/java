package com.yangliuxin.controller;

import com.google.common.collect.Maps;
import com.yangliuxin.annotation.LogAnnotation;
import com.yangliuxin.application.RoleService;
import com.yangliuxin.domain.Role;
import com.yangliuxin.page.PageTableHandler;
import com.yangliuxin.page.PageTableHandler.CountHandler;
import com.yangliuxin.page.PageTableHandler.ListHandler;
import com.yangliuxin.page.PageTableRequest;
import com.yangliuxin.page.PageTableResponse;
import com.yangliuxin.repository.RoleRepository;
import com.yangliuxin.vo.RoleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "角色")
@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleRepository roleRepository;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "保存角色")
	@PreAuthorize("hasAuthority('sys:role:add')")
	public void saveRole(@RequestBody RoleDto roleDto) {
		roleService.saveRole(roleDto);
	}

	@GetMapping
	@ApiOperation(value = "角色列表")
	@PreAuthorize("hasAuthority('sys:role:query')")
	public PageTableResponse listRoles(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return roleRepository.count(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<Role> list(PageTableRequest request) {
				List<Role> list = roleRepository.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取角色")
	@PreAuthorize("hasAuthority('sys:role:query')")
	public Role get(@PathVariable Long id) {
		return roleRepository.getById(id);
	}

	@GetMapping("/all")
	@ApiOperation(value = "所有角色")
	@PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
	public List<Role> roles() {
		return roleRepository.list(Maps.newHashMap(), null, null);
	}

	@GetMapping(params = "userId")
	@ApiOperation(value = "根据用户id获取拥有的角色")
	@PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
	public List<Role> roles(Long userId) {
		return roleRepository.listByUserId(userId);
	}

	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除角色")
	@PreAuthorize("hasAuthority('sys:role:del')")
	public void delete(@PathVariable Long id) {
		roleService.deleteRole(id);
	}
}
