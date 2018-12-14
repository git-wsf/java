package com.yangliuxin.repository;

import com.yangliuxin.domain.FileInfo;

import java.util.List;
import java.util.Map;

public interface FileInfoRepository {

    FileInfo getById(String id);

    int save(FileInfo fileInfo);

    int update(FileInfo fileInfo);

    int delete(String id);

    int count(Map<String, Object> params);

    List<FileInfo> list(Map<String, Object> params, Integer offset, Integer limit);
}
