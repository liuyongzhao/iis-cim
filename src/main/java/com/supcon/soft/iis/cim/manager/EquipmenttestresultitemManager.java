package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmenttestresultitemEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.Equipmenttestresultitem;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmenttestresultitemRepository;
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
public class EquipmenttestresultitemManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmenttestresultitemRepository equipmenttestresultitemRepository;

    public List<EquipmenttestresultitemEntity> getEquipmenttestresultitemList() {
        return equipmenttestresultitemRepository.findAll();
    }

    public Equipmenttestresultitem getEquipmenttestresultitemById(int id) {
        try {
            String jsonString = getKey(Equipmenttestresultitem.class.toString() + id);
            Equipmenttestresultitem obj;
            if (jsonString == null) {
                EquipmenttestresultitemEntity entity = equipmenttestresultitemRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
               /* List<EquipmenttestresultitemEntity> items = equipmenttestresultitemRepository.getInUseListById(id);
                List<Equipmenttestresultitem> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));
                obj.setItems(list);*/
                setKey(Equipmenttestresultitem.class.toString() + obj.getId(), obj);
                obj = objectMapper.readValue(getKey(Equipmenttestresultitem.class.toString() + id), Equipmenttestresultitem.class);
                System.out.println(obj);
                return obj;
                }
                obj = objectMapper.readValue(jsonString, Equipmenttestresultitem.class);
                return obj;
        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmenttestresultitemEntity createEquipmenttestresultitem(EquipmenttestresultitemEntity equipmenttestresultitem, Submitter submitter) {
        equipmenttestresultitem.setCreator(submitter);
        equipmenttestresultitemRepository.saveAndFlush(equipmenttestresultitem);
        return equipmenttestresultitemRepository.findById(equipmenttestresultitem.getId()).orElse(null);
    }

    public EquipmenttestresultitemEntity updateEquipmenttestresultitem(EquipmenttestresultitemEntity equipmenttestresultitem, Submitter submitter) {
        equipmenttestresultitem.setUpdater(submitter);
        return equipmenttestresultitemRepository.saveAndFlush(equipmenttestresultitem);
    }

    public Boolean deleteEquipmenttestresultitem(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmenttestresultitemEntity entity = equipmenttestresultitemRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            equipmenttestresultitemRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, Equipmenttestresultitem value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(Equipmenttestresultitem.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private Equipmenttestresultitem entityToObject(EquipmenttestresultitemEntity entity) {
        if (entity == null) {
            return null;
        }
        Equipmenttestresultitem obj = new Equipmenttestresultitem();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getEquipmentPropertyTagId()) {
            obj.setEquipmentPropertyTagId(entity.getEquipmentPropertyTagId());
        }
        if (null != entity.getEquipmentId()) {
            obj.setEquipmentId(entity.getEquipmentId());
        }
        if (null != entity.getEquipmentPropertyId()) {
            obj.setEquipmentPropertyId(entity.getEquipmentPropertyId());
        }
        if (null != entity.getResultId()) {
            obj.setResultId(entity.getResultId());
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
