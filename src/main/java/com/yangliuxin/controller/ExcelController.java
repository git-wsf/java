package com.yangliuxin.controller;


import com.yangliuxin.annotation.LogAnnotation;
import com.yangliuxin.application.ExcelService;
import com.yangliuxin.domain.Excel;
import com.yangliuxin.page.PageTableHandler;
import com.yangliuxin.page.PageTableRequest;
import com.yangliuxin.page.PageTableResponse;
import com.yangliuxin.repository.ExcelRepository;
import com.yangliuxin.vo.LayuiFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(tags = "数据导入")
@RestController
@RequestMapping("/import")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private ExcelRepository excelRepository;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "数据导入")
    public Excel uploadFile(MultipartFile file) throws IOException {
        return excelService.save(file);
    }

    /**
     * layui富文本文件自定义上传
     *
     * @param file
     * @param domain
     * @return
     * @throws IOException
     */
    @LogAnnotation
    @PostMapping("/layui")
    @ApiOperation(value = "layui富文本文件自定义上传")
    public LayuiFile uploadLayuiFile(MultipartFile file, String domain) throws IOException {
        Excel excel = excelService.save(file);

        LayuiFile layuiFile = new LayuiFile();
        layuiFile.setCode(0);
        LayuiFile.LayuiFileData data = new LayuiFile.LayuiFileData();
        layuiFile.setData(data);
        data.setSrc(domain + "/statics" + excel.getUrl());
        data.setTitle(file.getOriginalFilename());

        return layuiFile;
    }

    @GetMapping
    @ApiOperation(value = "文件查询")
    @PreAuthorize("hasAuthority('sys:file:query')")
    public PageTableResponse listFiles(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return excelRepository.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<Excel> list(PageTableRequest request) {
                List<Excel> list = excelRepository.list(request.getParams(), request.getOffset(), request.getLimit());
                return list;
            }
        }).handle(request);
    }

    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "文件删除")
    @PreAuthorize("hasAuthority('sys:file:del')")
    public void delete(@PathVariable String id) {
        excelService.delete(id);
    }

}
