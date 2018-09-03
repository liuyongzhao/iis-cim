package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.BaseCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/*
*
 * BaseCode [DAO]
 * @author qiyuqi*/


public interface BaseCodeRepository extends JpaRepository<BaseCodeEntity, Integer> {
    @Query(value = "select I from #{#entityName} I where baseCodeId = ?1 and deleted is null and used = true ")
    List<BaseCodeEntity> getInUseListByBaseCodeId(int baseCodeId);

}
