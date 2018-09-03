package com.supcon.soft.iis.cim.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.entity.EquipmentPhysicalassetEntity;
import com.supcon.soft.iis.cim.exception.InfoNotFoundException;
import com.supcon.soft.iis.cim.model.EquipmentPhysicalasset;
import com.supcon.soft.iis.cim.model.Submitter;
import com.supcon.soft.iis.cim.repository.EquipmentPhysicalassetRepository;
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
public class EquipmentPhysicalassetManager {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private EquipmentPhysicalassetRepository equipmentPhysicalassetRepository;

    public List<EquipmentPhysicalassetEntity> getEquipmentPhysicalassetList() {
        return equipmentPhysicalassetRepository.findAll();
    }

    public EquipmentPhysicalasset getEquipmentPhysicalassetById(int id) {
        try {
            String jsonString = getKey(EquipmentPhysicalasset.class.toString() + id);
            EquipmentPhysicalasset obj;
            if (jsonString == null) {
                EquipmentPhysicalassetEntity entity = equipmentPhysicalassetRepository.findById(id).orElse(null);
                if (entity == null) {
                    throw new InfoNotFoundException();
                }
                obj = entityToObject(entity);
                List<EquipmentPhysicalassetEntity> items = equipmentPhysicalassetRepository.getInUseListById(id);

                List<EquipmentPhysicalasset> list = new ArrayList<>();
                items.forEach(item -> list.add(entityToObject(item)));

                obj.setItems(list);

                setKey(EquipmentPhysicalasset.class.toString() + obj.getId(), obj);
                obj = objectMapper.readValue(getKey(EquipmentPhysicalasset.class.toString() + id), EquipmentPhysicalasset.class);
                System.out.println(obj);
                return obj;
            }
            obj = objectMapper.readValue(jsonString, EquipmentPhysicalasset.class);
            return obj;

        } catch (IOException ex) {
            //TODO: unhandle exception
            return null;
        }
    }
    public EquipmentPhysicalassetEntity createEquipmentPhysicalasset(EquipmentPhysicalassetEntity equipmentPhysicalasset, Submitter submitter) {
        equipmentPhysicalasset.setCreator(submitter);
        equipmentPhysicalassetRepository.saveAndFlush(equipmentPhysicalasset);
        return equipmentPhysicalassetRepository.findById(equipmentPhysicalasset.getId()).orElse(null);
    }

    public EquipmentPhysicalassetEntity updateEquipmentPhysicalasset(EquipmentPhysicalassetEntity equipmentPhysicalasset, Submitter submitter) {
        equipmentPhysicalasset.setUpdater(submitter);
        return equipmentPhysicalassetRepository.saveAndFlush(equipmentPhysicalasset);
    }

    public Boolean deleteEquipmentPhysicalasset(Integer id, Submitter submitter) throws InfoNotFoundException {
        EquipmentPhysicalassetEntity entity = equipmentPhysicalassetRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new InfoNotFoundException();
        } else {
            entity.setDeleted(new Date());
            entity.setDeleter(submitter);
            entity.setUsed(false);
            equipmentPhysicalassetRepository.saveAndFlush(entity);
            return true;
        }
    }

    private String getKey(String key) {
        ValueOperations<String, String> op = redisTemplate.opsForValue();
        return op.get(key);
    }
    private void setKey(String key, EquipmentPhysicalasset value) {
        try {
            String obj = objectMapper.writeValueAsString(value);
            ValueOperations<String, String> op = redisTemplate.opsForValue();
            op.set(EquipmentPhysicalasset.class.toString() + value.getId(), obj);
        } catch (JsonProcessingException ex) {
            log.info(ex.toString());
        }
    }
    private EquipmentPhysicalasset entityToObject(EquipmentPhysicalassetEntity entity) {
        if (entity == null) {
            return null;
        }
        EquipmentPhysicalasset obj = new EquipmentPhysicalasset();
        if (null != entity.getId()) {
            obj.setId(entity.getId());
        }
        if (null != entity.getEquipmentId()) {
            obj.setEquipment_id(entity.getEquipmentId());
        }
        if (null != entity.getPhysicalAssetId()) {
            obj.setPhysical_asset_id(entity.getPhysicalAssetId());
        }
        if (null != entity.getExpired()) {
            obj.setExpired(entity.getExpired());
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
