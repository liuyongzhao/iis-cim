package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmentClassEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.EquipmentClass;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class EquipmentClassManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmentClassRepository equipmentClassRepository;

    public List<EquipmentClassEntity> getEquipmentClassList() {
        return equipmentClassRepository.findAll();
    }

    public EquipmentClass getEquipmentClassById(int id) {
        try {
            String jsonString = getKey(EquipmentClass.class.toString() + id);
            EquipmentClass obj;
            if (jsonString == null) {
                EquipmentClassEntity entity = equipmentClassRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
                List<EquipmentClassEntity> items = equipmentClassRepository.getInUseListById(id);
                List<EquipmentClass> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));
                obj.setItems(list);
                setKey(EquipmentClass.class.toString() + obj.getId(), obj);
                obj = objectMapper.readValue(getKey(EquipmentClass.class.toString() + id), EquipmentClass.class);
                System.out.println(obj);
                return obj;
            }
            obj = objectMapper.readValue(jsonString, EquipmentClass.class);
            return obj;


        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmentClassEntity createEquipmentClass(EquipmentClassEntity equipmentClass, Submitter submitter) {
        equipmentClass.setCreator(submitter);
        equipmentClassRepository.saveAndFlush(equipmentClass);
        return equipmentClassRepository.findById(equipmentClass.getId()).orElse(null);
    }

    public EquipmentClassEntity updateEquipmentClass(EquipmentClassEntity equipmentClass, Submitter submitter) {
        equipmentClass.setUpdater(submitter);
        return equipmentClassRepository.saveAndFlush(equipmentClass);
    }

    public Boolean deleteEquipmentClass(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmentClassEntity entity = equipmentClassRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            entity.setUsed(false);
            equipmentClassRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, EquipmentClass value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(EquipmentClass.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private EquipmentClass entityToObject(EquipmentClassEntity entity) {
        if (entity == null) {
            return null;
        }
        EquipmentClass obj = new EquipmentClass();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getName()) {
            obj.setName(entity.getName());
        }
        if (null != entity.getDescription()) {
            obj.setDescription(entity.getDescription());
        }
        if (null != entity.getCreated()) {
            obj.setCreated(entity.getCreated());
        }
        if (null != entity.getUpdated()) {
            obj.setUpdated(entity.getUpdated());
        }
        if (null != entity.getDeleted()) {
            obj.setDeleted(entity.getDeleted());
        }
        if (null != entity.getUsed()) {
            obj.setUsed(entity.getUsed());
        }
        if (null != entity.getCreator()) {
            obj.setCreator(entity.getCreator());
        }
        if (null != entity.getUpdater()) {
            obj.setUpdater(entity.getUpdater());
        }
        if (null != entity.getDeleter()) {
            obj.setDeleter(entity.getDeleter());
        }
        return obj;
    }

}
