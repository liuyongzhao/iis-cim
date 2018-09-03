package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Integer> {
    @Query(value = "select I from #{#entityName} I where id = ?1 and deleted is null and used = true ")
    List<EquipmentEntity> getInUseListById(int id);

}
