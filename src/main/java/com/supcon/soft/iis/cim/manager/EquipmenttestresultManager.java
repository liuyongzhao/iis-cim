package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmenttestresultEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.Equipmenttestresult;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmenttestresultRepository;
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
public class EquipmenttestresultManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmenttestresultRepository equipmenttestresultRepository;

    public List<EquipmenttestresultEntity> getEquipmenttestresultList() {
        return equipmenttestresultRepository.findAll();
    }

    public Equipmenttestresult getEquipmenttestresultById(int id) {
        try {
            String jsonString = getKey(Equipmenttestresult.class.toString() + id);
            Equipmenttestresult obj;
            if (jsonString == null) {
                EquipmenttestresultEntity entity = equipmenttestresultRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
              /*  List<EquipmenttestresultEntity> items = equipmenttestresultRepository.getInUseListById(id);
                List<Equipmenttestresult> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));
                obj.setItems(list);*/
                setKey(Equipmenttestresult.class.toString() + obj.getId(), obj);
                obj = objectMapper.readValue(getKey(Equipmenttestresult.class.toString() + id), Equipmenttestresult.class);
                System.out.println(obj);
                return obj;
            }
            obj = objectMapper.readValue(jsonString, Equipmenttestresult.class);
            return obj;

        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmenttestresultEntity createEquipmenttestresult(EquipmenttestresultEntity equipmenttestresult, Submitter submitter) {
        equipmenttestresult.setCreator(submitter);
        equipmenttestresultRepository.saveAndFlush(equipmenttestresult);
        return equipmenttestresultRepository.findById(equipmenttestresult.getId()).orElse(null);
    }

    public EquipmenttestresultEntity updateEquipmenttestresult(EquipmenttestresultEntity equipmenttestresult, Submitter submitter) {
        equipmenttestresult.setUpdater(submitter);
        return equipmenttestresultRepository.saveAndFlush(equipmenttestresult);
    }

    public Boolean deleteEquipmenttestresult(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmenttestresultEntity entity = equipmenttestresultRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            equipmenttestresultRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, Equipmenttestresult value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(Equipmenttestresult.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private Equipmenttestresult entityToObject(EquipmenttestresultEntity entity) {
        if (entity == null) {
            return null;
        }
        Equipmenttestresult obj = new Equipmenttestresult();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getSpecificationId()) {
            obj.setSpecificationId(entity.getSpecificationId());
        }
        if (null != entity.getTestdate()) {
            obj.setTestdate(entity.getTestdate());
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
