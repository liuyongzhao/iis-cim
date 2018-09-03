package com.supcon.soft.iis.cim.repository;


import com.supcon.soft.iis.cim.entity.EquipmentClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EquipmentClassRepository extends JpaRepository<EquipmentClassEntity, Integer> {
    @Query(value = "select I from #{#entityName} I where Id = ?1 and deleted is null and used = true ")
    List<EquipmentClassEntity> getInUseListById(int id);
}
