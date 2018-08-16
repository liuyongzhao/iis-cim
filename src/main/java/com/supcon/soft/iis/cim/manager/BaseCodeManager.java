package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.BaseCodeEntity;
import com.supcon.soft.iis.cim.entity.BaseCodeItemEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.BaseCode;
import com.supcon.soft.iis.cim.model.BaseCodeItem;
import com.supcon.soft.iis.cim.repository.BaseCodeItemRepository;
import com.supcon.soft.iis.cim.repository.BaseCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class BaseCodeManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private BaseCodeRepository baseCodeRepository;

    @Autowired
    private BaseCodeItemRepository baseCodeItemRepository;

    public BaseCode getBaseCodeById(int id) throws InfoNotFoundException {
        try {
            BaseCode obj = objectMapper.readValue(getKey(BaseCode.class.toString() + id), BaseCode.class);
            if(null == obj) {
                BaseCodeEntity entity = baseCodeRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException(BaseCode.class.toString(),String.valueOf(id));
                }
                obj = EntityToObject(entity);
                List<BaseCodeItemEntity> items = baseCodeItemRepository.getInUseListByBaseCodeId(id);
                List<BaseCodeItem> list = new ArrayList<>();
                items.forEach(item -> list.add(EntityToObject(item)));
                obj.setItems(list);
                setKey(BaseCode.class.toString() + obj.getId(), obj);
            }
            return obj;
        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }

    private void setKey(String key, BaseCode value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(BaseCode.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }

    private BaseCodeEntity ObjectToEntity(BaseCode obj) {
        if (obj == null) return null;
        BaseCodeEntity entity = new BaseCodeEntity();
        if (null != obj.getId()) entity.setId(obj.getId());
        if (null != obj.getName()) entity.setName(obj.getName());
        if (null != obj.getDescription()) entity.setDescription(obj.getDescription());
        if (null != obj.getCreated()) entity.setCreated(obj.getCreated());
        if (null != obj.getUpdated()) entity.setUpdated(obj.getUpdated());
        if (null != obj.getDeleted()) entity.setDeleted(obj.getDeleted());
        if (null != obj.getUsed()) entity.setUsed(obj.getUsed());
        if (null != obj.getCreator()) entity.setCreator(obj.getCreator());
        if (null != obj.getUpdater()) entity.setUpdater(obj.getUpdater());
        if (null != obj.getDeleter()) entity.setDeleter(obj.getDeleter());
        return entity;
    }

    private BaseCode EntityToObject(BaseCodeEntity entity) {
        if (entity == null) return null;
        BaseCode obj = new BaseCode();
        if (null != entity.getId()) obj.setId(entity.getId());
        if (null != entity.getName()) obj.setName(entity.getName());
        if (null != entity.getDescription()) obj.setDescription(entity.getDescription());
        if (null != entity.getCreated()) obj.setCreated(entity.getCreated());
        if (null != entity.getUpdated()) obj.setUpdated(entity.getUpdated());
        if (null != entity.getDeleted()) obj.setDeleted(entity.getDeleted());
        if (null != entity.getUsed()) obj.setUsed(entity.getUsed());
        if (null != entity.getCreator()) obj.setCreator(entity.getCreator());
        if (null != entity.getUpdater()) obj.setUpdater(entity.getUpdater());
        if (null != entity.getDeleter()) obj.setDeleter(entity.getDeleter());
        return obj;
    }

    private BaseCodeItemEntity ObjectToEntity(BaseCodeItem obj) {
        if (obj == null) return null;
        BaseCodeItemEntity entity = new BaseCodeItemEntity();
        if (null != obj.getId()) entity.setId(obj.getId());
        if (null != obj.getCode()) entity.setCode(obj.getCode());
        if (null != obj.getValue()) entity.setValue(obj.getValue());
        if (null != obj.getBaseCodeId()) entity.setBaseCodeId(obj.getBaseCodeId());
        if (null != obj.getParentId()) entity.setParentId(obj.getParentId());
        if (null != obj.getCreated()) entity.setCreated(obj.getCreated());
        if (null != obj.getUpdated()) entity.setUpdated(obj.getUpdated());
        if (null != obj.getDeleted()) entity.setDeleted(obj.getDeleted());
        if (null != obj.getUsed()) entity.setUsed(obj.getUsed());
        if (null != obj.getCreator()) entity.setCreator(obj.getCreator());
        if (null != obj.getUpdater()) entity.setUpdater(obj.getUpdater());
        if (null != obj.getDeleter()) entity.setDeleter(obj.getDeleter());
        return entity;
    }

    private BaseCodeItem EntityToObject(BaseCodeItemEntity entity) {
        if (entity == null) return null;
        BaseCodeItem obj = new BaseCodeItem();
        if (null != entity.getId()) obj.setId(entity.getId());
        if (null != entity.getCode()) obj.setCode(entity.getCode());
        if (null != entity.getValue()) obj.setValue(entity.getValue());
        if (null != entity.getBaseCodeId()) obj.setBaseCodeId(entity.getBaseCodeId());
        if (null != entity.getParentId()) obj.setParentId(entity.getParentId());
        if (null != entity.getCreated()) obj.setCreated(entity.getCreated());
        if (null != entity.getUpdated()) obj.setUpdated(entity.getUpdated());
        if (null != entity.getDeleted()) obj.setDeleted(entity.getDeleted());
        if (null != entity.getUsed()) obj.setUsed(entity.getUsed());
        if (null != entity.getCreator()) obj.setCreator(entity.getCreator());
        if (null != entity.getUpdater()) obj.setUpdater(entity.getUpdater());
        if (null != entity.getDeleter()) obj.setDeleter(entity.getDeleter());
        return obj;
    }
}
