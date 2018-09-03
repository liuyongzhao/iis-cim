package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmentpropertytagEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.Equipmentpropertytag;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentpropertytagRepository;
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
public class EquipmentpropertytagManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmentpropertytagRepository equipmentpropertytagRepository;

    public List<EquipmentpropertytagEntity> getEquipmentpropertytagList() {
        return equipmentpropertytagRepository.findAll();
    }

    public Equipmentpropertytag getEquipmentpropertytagById(int id) {
        try {
            String jsonString = getKey(Equipmentpropertytag.class.toString() + id);
            Equipmentpropertytag obj;
            if (jsonString == null) {
                EquipmentpropertytagEntity entity = equipmentpropertytagRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
                List<EquipmentpropertytagEntity> items = equipmentpropertytagRepository.getInUseListById(id);

                /*List<Equipmentpropertytag> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));
                obj.setItems(list);*/

                setKey(Equipmentpropertytag.class.toString() + obj.getId(), obj);
                System.out.println(getKey(Equipmentpropertytag.class.toString() + obj.getId()));
                obj = objectMapper.readValue(getKey(Equipmentpropertytag.class.toString() + id), Equipmentpropertytag.class);
                System.out.println(obj);
                return obj;
            }
            obj = objectMapper.readValue(jsonString, Equipmentpropertytag.class);
            return obj;

        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmentpropertytagEntity createEquipmentpropertytag(EquipmentpropertytagEntity equipmentpropertytag, Submitter submitter) {
        equipmentpropertytag.setCreator(submitter);
        equipmentpropertytagRepository.saveAndFlush(equipmentpropertytag);
        return equipmentpropertytagRepository.findById(equipmentpropertytag.getId()).orElse(null);
    }

    public EquipmentpropertytagEntity updateEquipmentpropertytag(EquipmentpropertytagEntity equipmentpropertytag, Submitter submitter) {
        equipmentpropertytag.setUpdater(submitter);
        return equipmentpropertytagRepository.saveAndFlush(equipmentpropertytag);
    }

    public Boolean deleteEquipmentpropertytag(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmentpropertytagEntity entity = equipmentpropertytagRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            entity.setUsed(false);
            equipmentpropertytagRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, Equipmentpropertytag value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(Equipmentpropertytag.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private Equipmentpropertytag entityToObject(EquipmentpropertytagEntity entity) {
        if (entity == null) {
            return null;
        }
        Equipmentpropertytag obj = new Equipmentpropertytag();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getDescription()) {
            obj.setDescription(entity.getDescription());
        }
        if (null != entity.getName()) {
            obj.setName(entity.getName());
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
