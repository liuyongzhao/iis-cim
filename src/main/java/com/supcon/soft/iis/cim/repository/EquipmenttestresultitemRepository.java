package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.EquipmenttestresultitemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EquipmenttestresultitemRepository extends JpaRepository<EquipmenttestresultitemEntity, Integer>
    {
        @Query(value = "select I from #{#entityName} I where id = ?1 and deleted is null and used = true ")
        List<EquipmenttestresultitemEntity> getInUseListById(int id);
}
