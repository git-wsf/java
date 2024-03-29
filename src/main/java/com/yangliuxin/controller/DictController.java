package com.yangliuxin.controller;

import com.yangliuxin.domain.Dict;
import com.yangliuxin.page.PageTableHandler;
import com.yangliuxin.page.PageTableHandler.CountHandler;
import com.yangliuxin.page.PageTableHandler.ListHandler;
import com.yangliuxin.page.PageTableRequest;
import com.yangliuxin.page.PageTableResponse;
import com.yangliuxin.repository.DictRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "字典")
@RestController
@RequestMapping("/dicts")
public class DictController {

	@Autowired
	private DictRepository dictRepository;

	@PreAuthorize("hasAuthority('dict:add')")
	@PostMapping
	@ApiOperation(value = "保存")
	public Dict save(@RequestBody Dict dict) {
		Dict d = dictRepository.getByTypeAndK(dict.getType(), dict.getK());
		if (d != null) {
			throw new IllegalArgumentException("类型和key已存在");
		}
		dictRepository.save(dict);

		return dict;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取")
	public Dict get(@PathVariable Long id) {
		return dictRepository.getById(id);
	}

	@PreAuthorize("hasAuthority('dict:add')")
	@PutMapping
	@ApiOperation(value = "修改")
	public Dict update(@RequestBody Dict dict) {
		dictRepository.update(dict);

		return dict;
	}

	@PreAuthorize("hasAuthority('dict:query')")
	@GetMapping(params = { "start", "length" })
	@ApiOperation(value = "列表")
	public PageTableResponse list(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return dictRepository.count(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<Dict> list(PageTableRequest request) {
				return dictRepository.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).handle(request);
	}

	@PreAuthorize("hasAuthority('dict:del')")
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除")
	public void delete(@PathVariable Long id) {
		dictRepository.delete(id);
	}

	@GetMapping(params = "type")
	public List<Dict> listByType(String type) {
		return dictRepository.listByType(type);
	}
}
