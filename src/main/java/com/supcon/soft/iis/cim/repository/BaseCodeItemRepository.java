
package com.supcon.soft.iis.cim.repository;

import com.supcon.soft.iis.cim.entity.BaseCodeItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * BaseCode Repository
 * for operate database
 * @author qiyuqi
 */

public interface BaseCodeItemRepository extends JpaRepository<BaseCodeItemEntity, Integer> {

    /**
     * Get BaseCodeItem with usable condition.
     * <ul><li>deleted is null;</li><li>used is true</li></ul>
     * @param baseCodeId 基础代码编码
     * @return List<BaseCodeItemEntity>
     */
    @Query(value = "select I from #{#entityName} I where baseCodeId = ?1 and deleted is null and used = true ")
    List<BaseCodeItemEntity> getInUseListByBaseCodeId(int baseCodeId);
}


