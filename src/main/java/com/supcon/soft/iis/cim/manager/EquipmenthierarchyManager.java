package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmenthierarchyEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.Equipmenthierarchy;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmenthierarchyRepository;
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
public class EquipmenthierarchyManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmenthierarchyRepository equipmenthierarchyRepository;

    public List<EquipmenthierarchyEntity> getEquipmenthierarchyList() {
        return equipmenthierarchyRepository.findAll();
    }

    public Equipmenthierarchy getEquipmenthierarchyById(int id) {
        try {
            String jsonString = getKey(Equipmenthierarchy.class.toString() + id);
            Equipmenthierarchy obj;
            if (jsonString == null) {
                EquipmenthierarchyEntity entity = equipmenthierarchyRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
                List<EquipmenthierarchyEntity> items = equipmenthierarchyRepository.getInUseListById(id);
                List<Equipmenthierarchy> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));
                obj.setItems(list);
                setKey(Equipmenthierarchy.class.toString() + obj.getId(), obj);
                obj = objectMapper.readValue(getKey(Equipmenthierarchy.class.toString() + id), Equipmenthierarchy.class);
                System.out.println(obj);
                return obj;
            }
            obj = objectMapper.readValue(jsonString, Equipmenthierarchy.class);
            return obj;

        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmenthierarchyEntity createEquipmenthierarchy(EquipmenthierarchyEntity equipmenthierarchy, Submitter submitter) {
        equipmenthierarchy.setCreator(submitter);
        equipmenthierarchyRepository.saveAndFlush(equipmenthierarchy);
        return equipmenthierarchyRepository.findById(equipmenthierarchy.getId()).orElse(null);
    }

    public EquipmenthierarchyEntity updateEquipmenthierarchy(EquipmenthierarchyEntity equipmenthierarchy, Submitter submitter) {
        equipmenthierarchy.setUpdater(submitter);
        return equipmenthierarchyRepository.saveAndFlush(equipmenthierarchy);
    }

    public Boolean deleteEquipmenthierarchy(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmenthierarchyEntity entity = equipmenthierarchyRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            entity.setUsed(false);
            equipmenthierarchyRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, Equipmenthierarchy value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(Equipmenthierarchy.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private Equipmenthierarchy entityToObject(EquipmenthierarchyEntity entity) {
        if (entity == null) {
            return null;
        }
        Equipmenthierarchy obj = new Equipmenthierarchy();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getCode()) {
            obj.setCode(entity.getCode());
        }
        if (null != entity.getDescription()){
            obj.setDescription(entity.getDescription());
        }
        if (null != entity.getEquipmentType()){
            obj.setEquipment_type(entity.getEquipmentType());
        }
        if (null != entity.getName()){
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
