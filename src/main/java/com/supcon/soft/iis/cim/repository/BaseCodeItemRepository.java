package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.BaseCodeItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BaseCodeItemRepository extends JpaRepository<BaseCodeItemEntity, Integer> {
    @Query(value = "select I from #{#entityName} I where baseCodeId = ?1 and deleted is null and used = true ")
    List<BaseCodeItemEntity> getInUseListByBaseCodeId(int baseCodeId);
}
