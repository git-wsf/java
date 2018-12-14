package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.FileInfo;
import com.yangliuxin.repository.FileInfoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class FileInfoRepositoryImpl implements FileInfoRepository {
    @Override
    public FileInfo getById(String id) {
        return null;
    }

    @Override
    public int save(FileInfo fileInfo) {
        return 0;
    }

    @Override
    public int update(FileInfo fileInfo) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int count(Map<String, Object> params) {
        return 0;
    }

    @Override
    public List<FileInfo> list(Map<String, Object> params, Integer offset, Integer limit) {
        return null;
    }
}
