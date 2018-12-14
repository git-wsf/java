package com.yangliuxin.repository;

import com.yangliuxin.domain.Dict;

import java.util.List;
import java.util.Map;

public interface DictRepository {

    Dict getById(Long id);

    int delete(Long id);

    int update(Dict dict);

    int save(Dict dict);

    int count(Map<String, Object> params);

    List<Dict> list(Map<String, Object> params, Integer offset, Integer limit);

    Dict getByTypeAndK(String type, String k);

    List<Dict> listByType(String type);
}
