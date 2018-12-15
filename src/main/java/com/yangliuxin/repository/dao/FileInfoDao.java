package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FileInfoDao extends JpaRepository<FileInfo, Long>, JpaSpecificationExecutor<FileInfo> {

    FileInfo findById(String id);
}
