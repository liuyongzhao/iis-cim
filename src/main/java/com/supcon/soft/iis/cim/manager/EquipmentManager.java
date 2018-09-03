package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmentEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.Equipment;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentRepository;
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
public class EquipmentManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<EquipmentEntity> getEquipmentList() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(int id) {
        try {
            String jsonString = getKey(Equipment.class.toString() + id);
            Equipment obj;
            if (jsonString == null) {
                EquipmentEntity entity = equipmentRepository.findById(id).orElse(null);
                System.out.println("22222222"+entity);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);

                List<EquipmentEntity> items = equipmentRepository.getInUseListById(id);

                List<Equipment> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));

                obj.setItems(list);

                setKey(Equipment.class.toString() + obj.getId(), obj);
                obj = objectMapper.readValue(getKey(Equipment.class.toString() + id), Equipment.class);
                System.out.println(obj);
                return obj;
            }
            obj = objectMapper.readValue(jsonString, Equipment.class);
            return obj;


        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmentEntity createEquipment(EquipmentEntity equipment, Submitter submitter) {
        equipment.setCreator(submitter);
        equipmentRepository.saveAndFlush(equipment);
        return equipmentRepository.findById(equipment.getId()).orElse(null);
    }

    public EquipmentEntity updateEquipment(EquipmentEntity equipment, Submitter submitter) {
        equipment.setUpdater(submitter);
        return equipmentRepository.saveAndFlush(equipment);
    }

    public Boolean deleteEquipment(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmentEntity entity = equipmentRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            entity.setUsed(false);
            equipmentRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, Equipment value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(Equipment.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private Equipment entityToObject(EquipmentEntity entity) {
        if (entity == null) {
            return null;
        }
        Equipment obj = new Equipment();
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
