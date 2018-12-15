package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FileInfoDao extends JpaRepository<FileInfo, Long>, JpaSpecificationExecutor<FileInfo> {



}
