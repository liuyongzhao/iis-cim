package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmentclassEquipmentpropertyEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.EquipmentclassEquipmentproperty;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentclassEquipmentpropertyRepository;
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
public class EquipmentclassEquipmentpropertyManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmentclassEquipmentpropertyRepository equipmentclassEquipmentpropertyRepository;

    public List<EquipmentclassEquipmentpropertyEntity> getEquipmentclassEquipmentpropertyList() {
        return equipmentclassEquipmentpropertyRepository.findAll();
    }

    public EquipmentclassEquipmentproperty getEquipmentclassEquipmentpropertyById(int id) {
        try {
            System.out.println(EquipmentclassEquipmentproperty.class.toString() );
            String jsonString = getKey(EquipmentclassEquipmentproperty.class.toString() + id);
            System.out.println(jsonString);
            EquipmentclassEquipmentproperty obj;
            if (jsonString == null) {
                EquipmentclassEquipmentpropertyEntity entity = equipmentclassEquipmentpropertyRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
                /*List<EquipmentclassEquipmentpropertyEntity> items = equipmentclassEquipmentpropertyRepository.getInUseListById(id);
                List<EquipmentclassEquipmentproperty> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));
                obj.setItems(list);*/
                setKey(EquipmentclassEquipmentproperty.class.toString() + obj.getId(), obj);
                System.out.println(getKey(EquipmentclassEquipmentproperty.class.toString() + obj.getId()));
                obj = objectMapper.readValue(getKey(EquipmentclassEquipmentproperty.class.toString()+ id), EquipmentclassEquipmentproperty.class);

                System.out.println(obj);
                return obj;
            }
            obj = objectMapper.readValue(jsonString, EquipmentclassEquipmentproperty.class);
            return obj;

        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmentclassEquipmentpropertyEntity createEquipmentclassEquipmentproperty(EquipmentclassEquipmentpropertyEntity equipmentclassEquipmentproperty, Submitter submitter) {
        equipmentclassEquipmentproperty.setCreator(submitter);
        equipmentclassEquipmentpropertyRepository.saveAndFlush(equipmentclassEquipmentproperty);
        return equipmentclassEquipmentpropertyRepository.findById(equipmentclassEquipmentproperty.getId()).orElse(null);
    }

    public EquipmentclassEquipmentpropertyEntity updateEquipmentclassEquipmentproperty(EquipmentclassEquipmentpropertyEntity equipmentclassEquipmentproperty, Submitter submitter) {
        equipmentclassEquipmentproperty.setUpdater(submitter);
        return equipmentclassEquipmentpropertyRepository.saveAndFlush(equipmentclassEquipmentproperty);
    }

    public Boolean deleteEquipmentclassEquipmentproperty(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmentclassEquipmentpropertyEntity entity = equipmentclassEquipmentpropertyRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            entity.setUsed(false);
            equipmentclassEquipmentpropertyRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        System.out.println(op.get(key));
        return op.get(key);
    }
    private void setKey(String key, EquipmentclassEquipmentproperty value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(EquipmentclassEquipmentproperty.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private EquipmentclassEquipmentproperty entityToObject(EquipmentclassEquipmentpropertyEntity entity) {
        if (entity == null) {
            return null;
        }
        EquipmentclassEquipmentproperty obj = new EquipmentclassEquipmentproperty();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getEquipmentClassId()) {
            obj.setEquipment_class_id(entity.getEquipmentClassId());
        }
        if (null != entity.getEquipmentPropertyId()){
            obj.setEquipment_property_id(entity.getEquipmentPropertyId());
        }
        if (null != entity.getEquipmentPropertyTagId()){
            obj.setEquipment_property_id(entity.getEquipmentPropertyTagId());
        }
        if (null != entity.getValidtime()){
            obj.setValidtime(entity.getValidtime());
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
