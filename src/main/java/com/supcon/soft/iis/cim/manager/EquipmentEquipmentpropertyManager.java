package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmentEquipmentpropertyEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.EquipmentEquipmentproperty;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentEquipmentpropertyRepository;
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
public class EquipmentEquipmentpropertyManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmentEquipmentpropertyRepository equipmentEquipmentpropertyRepository;

    public List<EquipmentEquipmentpropertyEntity> getEquipmentEquipmentpropertyList() {
        return equipmentEquipmentpropertyRepository.findAll();
    }

    public EquipmentEquipmentproperty getEquipmentEquipmentpropertyById(int id) {
        try {
            String jsonString = getKey(EquipmentEquipmentproperty.class.toString() + id);
            EquipmentEquipmentproperty obj;
            if (jsonString == null) {
                EquipmentEquipmentpropertyEntity entity = equipmentEquipmentpropertyRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
                List<EquipmentEquipmentpropertyEntity> items = equipmentEquipmentpropertyRepository.getInUseListById(id);
                List<EquipmentEquipmentproperty> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));
                obj.setItems(list);
                setKey(EquipmentEquipmentproperty.class.toString() + obj.getId(), obj);
                obj = objectMapper.readValue(getKey(EquipmentEquipmentproperty.class.toString() + id), EquipmentEquipmentproperty.class);
                System.out.println(obj);
                return obj;
            }
            obj = objectMapper.readValue(jsonString, EquipmentEquipmentproperty.class);
            return obj;

        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmentEquipmentpropertyEntity createEquipmentEquipmentproperty(EquipmentEquipmentpropertyEntity equipmentEquipmentproperty, Submitter submitter) {
        equipmentEquipmentproperty.setCreator(submitter);
        equipmentEquipmentpropertyRepository.saveAndFlush(equipmentEquipmentproperty);
        return equipmentEquipmentpropertyRepository.findById(equipmentEquipmentproperty.getId()).orElse(null);
    }

    public EquipmentEquipmentpropertyEntity updateEquipmentEquipmentproperty(EquipmentEquipmentpropertyEntity equipmentEquipmentproperty, Submitter submitter) {
        equipmentEquipmentproperty.setUpdater(submitter);
        return equipmentEquipmentpropertyRepository.saveAndFlush(equipmentEquipmentproperty);
    }

    public Boolean deleteEquipmentEquipmentproperty(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmentEquipmentpropertyEntity entity = equipmentEquipmentpropertyRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            entity.setUsed(false);
            equipmentEquipmentpropertyRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, EquipmentEquipmentproperty value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(EquipmentEquipmentproperty.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private EquipmentEquipmentproperty entityToObject(EquipmentEquipmentpropertyEntity entity) {
        if (entity == null) {
            return null;
        }
        EquipmentEquipmentproperty obj = new EquipmentEquipmentproperty();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getEquipmentId()) {
            obj.setEquipment_id(entity.getEquipmentId());
        }
        if (null != entity.getEquipmentPropertyId()){
            obj.setEquipment_property_id(entity.getEquipmentPropertyId());
        }
        if (null != entity.getEquipmentPropertyTagId()){
            obj.setEquipment_property_id(entity.getEquipmentPropertyTagId());
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
