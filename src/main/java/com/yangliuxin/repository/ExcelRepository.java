package com.yangliuxin.repository;

import com.yangliuxin.domain.Excel;

import java.util.List;
import java.util.Map;

public interface ExcelRepository {

    Excel getById(String id);

    int save(Excel fileInfo);

    int update(Excel fileInfo);

    int delete(String id);

    int count(Map<String, Object> params);

    List<Excel> list(Map<String, Object> params, Integer offset, Integer limit);
}
