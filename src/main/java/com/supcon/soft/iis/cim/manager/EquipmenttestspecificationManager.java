package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmenttestspecificationEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.Equipmenttestspecification;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmenttestspecificationRepository;
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
public class EquipmenttestspecificationManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmenttestspecificationRepository equipmenttestspecificationRepository;

    public List<EquipmenttestspecificationEntity> getEquipmenttestspecificationList() {
        return equipmenttestspecificationRepository.findAll();
    }

    public Equipmenttestspecification getEquipmenttestspecificationById(int id) {
        try {
            String jsonString = getKey(Equipmenttestspecification.class.toString() + id);
            Equipmenttestspecification obj;
            if (jsonString == null) {
                EquipmenttestspecificationEntity entity = equipmenttestspecificationRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
                List<EquipmenttestspecificationEntity> items = equipmenttestspecificationRepository.getInUseListById(id);
                List<Equipmenttestspecification> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));
                obj.setItems(list);
                setKey(Equipmenttestspecification.class.toString() + obj.getId(), obj);
                obj = objectMapper.readValue(getKey(Equipmenttestspecification.class.toString() + id), Equipmenttestspecification.class);
                System.out.println(obj);
                return obj;
                }
                obj = objectMapper.readValue(jsonString, Equipmenttestspecification.class);
                return obj;
        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmenttestspecificationEntity createEquipmenttestspecification(EquipmenttestspecificationEntity equipmenttestspecification, Submitter submitter) {
        equipmenttestspecification.setCreator(submitter);
        equipmenttestspecificationRepository.saveAndFlush(equipmenttestspecification);
        return equipmenttestspecificationRepository.findById(equipmenttestspecification.getId()).orElse(null);
    }

    public EquipmenttestspecificationEntity updateEquipmenttestspecification(EquipmenttestspecificationEntity equipmenttestspecification, Submitter submitter) {
        equipmenttestspecification.setUpdater(submitter);
        return equipmenttestspecificationRepository.saveAndFlush(equipmenttestspecification);
    }

    public Boolean deleteEquipmenttestspecification(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmenttestspecificationEntity entity = equipmenttestspecificationRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            equipmenttestspecificationRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, Equipmenttestspecification value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(Equipmenttestspecification.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private Equipmenttestspecification entityToObject(EquipmenttestspecificationEntity entity) {
        if (entity == null) {
            return null;
        }
        Equipmenttestspecification obj = new Equipmenttestspecification();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getDescription()) {
            obj.setDescription(entity.getDescription());
        }
        if (null != entity.getEquipmentClassId()) {
            obj.setEquipmentClassId(entity.getEquipmentClassId());
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
