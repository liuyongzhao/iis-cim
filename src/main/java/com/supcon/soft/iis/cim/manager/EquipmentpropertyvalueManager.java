package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmentpropertyvalueEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.Equipmentpropertyvalue;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentpropertyvalueRepository;
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
public class EquipmentpropertyvalueManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmentpropertyvalueRepository equipmentpropertyvalueRepository;

    public List<EquipmentpropertyvalueEntity> getEquipmentpropertyvalueList() {
        return equipmentpropertyvalueRepository.findAll();
    }

    public Equipmentpropertyvalue getEquipmentpropertyvalueById(int id) {
        try {
            String jsonString = getKey(Equipmentpropertyvalue.class.toString() + id);
            Equipmentpropertyvalue obj;
            if (jsonString == null) {
                EquipmentpropertyvalueEntity entity = equipmentpropertyvalueRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
                /*List<EquipmentpropertyvalueEntity> items = equipmentpropertyvalueRepository.getInUseListById(id);
                List<Equipmentpropertyvalue> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));
                obj.setItems(list);*/
                setKey(Equipmentpropertyvalue.class.toString() + obj.getId(), obj);
                obj = objectMapper.readValue(getKey(Equipmentpropertyvalue.class.toString() + id), Equipmentpropertyvalue.class);
                System.out.println(obj);
                return obj;
                }
                obj = objectMapper.readValue(jsonString, Equipmentpropertyvalue.class);
                return obj;
        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmentpropertyvalueEntity createEquipmentpropertyvalue(EquipmentpropertyvalueEntity equipmentpropertyvalue, Submitter submitter) {
        equipmentpropertyvalue.setCreator(submitter);
        equipmentpropertyvalueRepository.saveAndFlush(equipmentpropertyvalue);
        return equipmentpropertyvalueRepository.findById(equipmentpropertyvalue.getId()).orElse(null);
    }

    public EquipmentpropertyvalueEntity updateEquipmentpropertyvalue(EquipmentpropertyvalueEntity equipmentpropertyvalue, Submitter submitter) {
        equipmentpropertyvalue.setUpdater(submitter);
        return equipmentpropertyvalueRepository.saveAndFlush(equipmentpropertyvalue);
    }

    public Boolean deleteEquipmentpropertyvalue(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmentpropertyvalueEntity entity = equipmentpropertyvalueRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            equipmentpropertyvalueRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, Equipmentpropertyvalue value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(Equipmentpropertyvalue.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private Equipmentpropertyvalue entityToObject(EquipmentpropertyvalueEntity entity) {
        if (entity == null) {
            return null;
        }
        Equipmentpropertyvalue obj = new Equipmentpropertyvalue();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getEquipmentId()) {
            obj.setEquipmentId(entity.getEquipmentId());
        }
        if (null != entity.getEquipmentPropertyId()) {
            obj.setEquipmentPropertyId(entity.getEquipmentPropertyId());
        }
        if (null != entity.getEquipmentPropertyTagId()) {
            obj.setEquipmentPropertyTagId(entity.getEquipmentPropertyTagId());
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
        if (null != entity.getValue()) {
            obj.setValue(entity.getValue());
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
