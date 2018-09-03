package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmenttestresultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmenttestresultRepository extends JpaRepository<EquipmenttestresultEntity, Integer>{
        @Query(value = "select I from #{#entityName} I where id = ?1 and deleted is null and used = true ")
        List<EquipmenttestresultEntity> getInUseListById(int id);
}
