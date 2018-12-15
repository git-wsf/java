package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DictDao extends JpaRepository<Dict, Long>, JpaSpecificationExecutor<Dict> {

    @Query(value = "select * from tb_dict t where t.type = ?1 and k = ?2", nativeQuery = true)
    Dict getByTypeAndK(String type, String k);

    @Query(value = "select * from tb_dict t where t.type = ?1", nativeQuery = true)
    List<Dict> listByType(String type);


}


