package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.FileInfo;
import com.yangliuxin.domain.Notice;
import com.yangliuxin.repository.FileInfoRepository;
import com.yangliuxin.repository.dao.FileInfoDao;
import com.yangliuxin.repository.dao.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;


@Repository
public class FileInfoRepositoryImpl implements FileInfoRepository {


    @Autowired
    private FileInfoDao fileInfoDao;


    @Autowired
    private EntityManager entityManager;


    @Override
    public FileInfo getById(String id) {
        return fileInfoDao.findById(id);
    }

    @Override
    public int save(FileInfo fileInfo) {
        fileInfoDao.save(fileInfo);
        return 1;
    }

    @Override
    public int update(FileInfo fileInfo) {
        fileInfoDao.save(fileInfo);
        return 1;
    }

    @Override
    public int delete(String id) {
        fileInfoDao.delete(Long.parseLong(id));
        return 1;
    }

    @Override
    public int count(Map<String, Object> params) {
        return ((int) (fileInfoDao.count()));
    }

    @Override
    public List<FileInfo> list(Map<String, Object> params, Integer offset, Integer limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from file_info t ");

        if(params != null && params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy"))){
            sb.append(" ").append(params.get("orderBy")).append(" ");
        }
        if(offset != null && offset>0){
            sb.append(" limit ").append(offset);
        }

        if(limit != null && limit>0){
            sb.append(",  ").append(limit);
        }

        String sql = sb.toString();
        Query userQuery = entityManager.createNativeQuery(sql, FileInfo.class);
        return userQuery.getResultList();
    }
}
