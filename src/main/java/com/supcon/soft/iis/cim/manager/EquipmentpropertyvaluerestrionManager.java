package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmentpropertyvaluerestrionEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.Equipmentpropertyvaluerestrion;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentpropertyvaluerestrionRepository;
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
public class EquipmentpropertyvaluerestrionManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmentpropertyvaluerestrionRepository equipmentpropertyvaluerestrionRepository;

    public List<EquipmentpropertyvaluerestrionEntity> getEquipmentpropertyvaluerestrionList() {
        return equipmentpropertyvaluerestrionRepository.findAll();
    }

    public Equipmentpropertyvaluerestrion getEquipmentpropertyvaluerestrionById(int id) {
        try {
            String jsonString = getKey(Equipmentpropertyvaluerestrion.class.toString() + id);
            Equipmentpropertyvaluerestrion obj;
            if (jsonString == null) {
                EquipmentpropertyvaluerestrionEntity entity = equipmentpropertyvaluerestrionRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
               /* List<EquipmentpropertyvaluerestrionEntity> items = equipmentpropertyvaluerestrionRepository.getInUseListById(id);
                List<Equipmentpropertyvaluerestrion> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));
                obj.setItems(list);*/
                setKey(Equipmentpropertyvaluerestrion.class.toString() + obj.getId(), obj);
                obj = objectMapper.readValue(getKey(Equipmentpropertyvaluerestrion.class.toString() + id), Equipmentpropertyvaluerestrion.class);
                System.out.println(obj);
                return obj;
                }
                obj = objectMapper.readValue(jsonString, Equipmentpropertyvaluerestrion.class);
                return obj;
        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmentpropertyvaluerestrionEntity createEquipmentpropertyvaluerestrion(EquipmentpropertyvaluerestrionEntity equipmentpropertyvaluerestrion, Submitter submitter) {
        equipmentpropertyvaluerestrion.setCreator(submitter);
        equipmentpropertyvaluerestrionRepository.saveAndFlush(equipmentpropertyvaluerestrion);
        return equipmentpropertyvaluerestrionRepository.findById(equipmentpropertyvaluerestrion.getId()).orElse(null);
    }

    public EquipmentpropertyvaluerestrionEntity updateEquipmentpropertyvaluerestrion(EquipmentpropertyvaluerestrionEntity equipmentpropertyvaluerestrion, Submitter submitter) {
        equipmentpropertyvaluerestrion.setUpdater(submitter);
        return equipmentpropertyvaluerestrionRepository.saveAndFlush(equipmentpropertyvaluerestrion);
    }

    public Boolean deleteEquipmentpropertyvaluerestrion(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmentpropertyvaluerestrionEntity entity = equipmentpropertyvaluerestrionRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            equipmentpropertyvaluerestrionRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, Equipmentpropertyvaluerestrion value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(Equipmentpropertyvaluerestrion.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private Equipmentpropertyvaluerestrion entityToObject(EquipmentpropertyvaluerestrionEntity entity) {
        if (entity == null) {
            return null;
        }
        Equipmentpropertyvaluerestrion obj = new Equipmentpropertyvaluerestrion();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getCriteria()) {
            obj.setCriteria(entity.getCriteria());
        }
        if (null != entity.getEquipmentId()) {
            obj.setEquipmentId(entity.getEquipmentId());
        }
        if (null != entity.getEquipmentPropertyId()) {
            obj.setEquipmentPropertyId(entity.getEquipmentPropertyId());
        }
        if (null != entity.getValue()) {
            obj.setValue(entity.getValue());
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
