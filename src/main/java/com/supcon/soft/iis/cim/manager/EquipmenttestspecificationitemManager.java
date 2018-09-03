package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmenttestspecificationitemEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.Equipmenttestspecificationitem;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmenttestspecificationitemRepository;
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
public class EquipmenttestspecificationitemManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmenttestspecificationitemRepository equipmenttestspecificationitemRepository;

    public List<EquipmenttestspecificationitemEntity> getEquipmenttestspecificationitemList() {
        return equipmenttestspecificationitemRepository.findAll();
    }

    public Equipmenttestspecificationitem getEquipmenttestspecificationitemById(int id) {
        try {
            String jsonString = getKey(Equipmenttestspecificationitem.class.toString() + id);
            Equipmenttestspecificationitem obj;
            if (jsonString == null) {
                EquipmenttestspecificationitemEntity entity = equipmenttestspecificationitemRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
                List<EquipmenttestspecificationitemEntity> items = equipmenttestspecificationitemRepository.getInUseListById(id);
                List<Equipmenttestspecificationitem> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));
                obj.setItems(list);
                setKey(Equipmenttestspecificationitem.class.toString() + obj.getId(), obj);
                obj = objectMapper.readValue(getKey(Equipmenttestspecificationitem.class.toString() + id), Equipmenttestspecificationitem.class);
                System.out.println(obj);
                return obj;
                }
                obj = objectMapper.readValue(jsonString, Equipmenttestspecificationitem.class);
                return obj;
        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmenttestspecificationitemEntity createEquipmenttestspecificationitem(EquipmenttestspecificationitemEntity equipmenttestspecificationitem, Submitter submitter) {

        equipmenttestspecificationitemRepository.saveAndFlush(equipmenttestspecificationitem);
        return equipmenttestspecificationitemRepository.findById(equipmenttestspecificationitem.getId()).orElse(null);
    }

    public EquipmenttestspecificationitemEntity updateEquipmenttestspecificationitem(EquipmenttestspecificationitemEntity equipmenttestspecificationitem, Submitter submitter) {

        return equipmenttestspecificationitemRepository.saveAndFlush(equipmenttestspecificationitem);
    }

    public Boolean deleteEquipmenttestspecificationitem(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmenttestspecificationitemEntity entity = equipmenttestspecificationitemRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());

           equipmenttestspecificationitemRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, Equipmenttestspecificationitem value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(Equipmenttestspecificationitem.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private Equipmenttestspecificationitem entityToObject(EquipmenttestspecificationitemEntity entity) {
        if (entity == null) {
            return null;
        }
        Equipmenttestspecificationitem obj = new Equipmenttestspecificationitem();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getPropertyId()) {
            obj.setPropertyId(entity.getPropertyId());
        }
        if (null != entity.getPropertyTagId()) {
            obj.setPropertyTagId(entity.getPropertyTagId());
        }
        if (null != entity.getSpecificationId()) {
            obj.setSpecificationId(entity.getSpecificationId());
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
        return obj;
    }

}