package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmentpropertyEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.Equipmentproperty;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentpropertyRepository;
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
public class EquipmentpropertyManager {
        private final ObjectMapper objectMapper = new ObjectMapper();
        @Autowired
        private RedisTemplate<String, String> redisTemplate;
        @Autowired
        private EquipmentpropertyRepository equipmentpropertyRepository;

        public List<EquipmentpropertyEntity> getEquipmentpropertyList() {
            return equipmentpropertyRepository.findAll();
        }

        public Equipmentproperty getEquipmentpropertyById(int id) {
            try {
                String jsonString = getKey(Equipmentproperty.class.toString() + id);
                Equipmentproperty obj;
                if (jsonString == null) {
                    EquipmentpropertyEntity entity = equipmentpropertyRepository.findById(id).orElse(null);
                    if (entity == null) {
                        throw new InfoNotFoundException();
                    }
                    obj = entityToObject(entity);
                    List<EquipmentpropertyEntity> items = equipmentpropertyRepository.getInUseListById(id);
                    List<Equipmentproperty> list = new ArrayList<>();
                    items.forEach(item -> list.add(entityToObject(item)));
                    obj.setItems(list);
                    setKey(Equipmentproperty.class.toString() + obj.getId(), obj);
                    obj = objectMapper.readValue(getKey(Equipmentproperty.class.toString() + id), Equipmentproperty.class);
                    System.out.println(obj);
                    return obj;
                }
                obj = objectMapper.readValue(jsonString, Equipmentproperty.class);
                return obj;

            } catch (IOException ex) {
                //TODO: unhandle exception
                return null;
            }
        }
        public EquipmentpropertyEntity createEquipmentproperty(EquipmentpropertyEntity equipmentproperty, Submitter submitter) {
            equipmentproperty.setCreator(submitter);
            equipmentpropertyRepository.saveAndFlush(equipmentproperty);
            return equipmentpropertyRepository.findById(equipmentproperty.getId()).orElse(null);
        }

        public EquipmentpropertyEntity updateEquipmentproperty(EquipmentpropertyEntity equipmentproperty, Submitter submitter) {
            equipmentproperty.setUpdater(submitter);
            return equipmentpropertyRepository.saveAndFlush(equipmentproperty);
        }

        public Boolean deleteEquipmentproperty(Integer id, Submitter submitter) throws InfoNotFoundException {
            EquipmentpropertyEntity entity = equipmentpropertyRepository.findById(id).orElse(null);
            if (null == entity) {
                throw new InfoNotFoundException();
            } else {
                entity.setDeleted(new Date());
                entity.setDeleter(submitter);
                entity.setUsed(false);
                equipmentpropertyRepository.saveAndFlush(entity);
                return true;
            }
        }

        private String getKey(String key) {
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            return op.get(key);
        }
        private void setKey(String key, Equipmentproperty value) {
            try {
                String obj = objectMapper.writeValueAsString(value);
                ValueOperations<String, String> op = redisTemplate.opsForValue();
                op.set(Equipmentproperty.class.toString() + value.getId(), obj);
            } catch (JsonProcessingException ex) {
                log.info(ex.toString());
            }
        }
        private Equipmentproperty entityToObject(EquipmentpropertyEntity entity) {
            if (entity == null) {
                return null;
            }
            Equipmentproperty obj = new Equipmentproperty();
            if (null != entity.getId()) {
                obj.setId(entity.getId());
            }
            if (null != entity.getBaseCodeId()) {
                obj.setBase_code_id(entity.getBaseCodeId());
            }
            if (null != entity.getDefaultValue()) {
                obj.setDefault_value(entity.getDefaultValue());
            }
            if (null != entity.getDescription()) {
                obj.setDescription(entity.getDescription());
            }
            if (null != entity.getDimensionId()){
                obj.setDimension_id(entity.getDimensionId());
            }
            if (null != entity.getName()){
                obj.setName(entity.getName());
            }
            if (null != entity.getParentId()){
                obj.setParent_id(entity.getParentId());
            }
            if (null != entity.getUnitId()){
                obj.setUnit_id(entity.getUnitId());
            }
            if (null != entity.getValidtime()){
                obj.setValidtime(entity.getValidtime());
            }
            if (null != entity.getValuetype()){
                obj.setValuetype(entity.getValuetype());
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
