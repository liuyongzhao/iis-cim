package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmentpropertyvaluerestrionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentpropertyvaluerestrionRepository extends JpaRepository<EquipmentpropertyvaluerestrionEntity, Integer> {
    @Query(value = "select I from #{#entityName} I where id = ?1 and deleted is null and used = true ")
    List<EquipmentpropertyvaluerestrionEntity> getInUseListById(int id);
}
