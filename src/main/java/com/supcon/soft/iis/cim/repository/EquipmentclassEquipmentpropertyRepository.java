package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmentclassEquipmentpropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentclassEquipmentpropertyRepository extends JpaRepository<EquipmentclassEquipmentpropertyEntity, Integer> {

    @Query(value = "select I from #{#entityName} I where id = ?1 and deleted is null and used = true ")
    List<EquipmentclassEquipmentpropertyEntity> getInUseListById(int id);}
