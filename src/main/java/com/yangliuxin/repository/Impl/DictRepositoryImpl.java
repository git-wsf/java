package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Dict;
import com.yangliuxin.repository.DictRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class DictRepositoryImpl implements DictRepository {
    @Override
    public Dict getById(Long id) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int update(Dict dict) {
        return 0;
    }

    @Override
    public int save(Dict dict) {
        return 0;
    }

    @Override
    public int count(Map<String, Object> params) {
        return 0;
    }

    @Override
    public List<Dict> list(Map<String, Object> params, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public Dict getByTypeAndK(String type, String k) {
        return null;
    }

    @Override
    public List<Dict> listByType(String type) {
        return null;
    }
}
