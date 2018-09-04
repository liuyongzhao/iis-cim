package com.supcon.soft.iis.cim.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supcon.soft.iis.cim.model.Submitter;

import javax.persistence.AttributeConverter;
import java.io.IOException;

/**
 * Attribute Convert for Submitter with JPA
 * @author qiyuqi
 */
public class JpaConvertSubmitterWithJson implements AttributeConverter<Submitter, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Submitter meta){
        try{
            return objectMapper.writeValueAsString(meta);
        }catch (JsonProcessingException ex){
            return null;
        }
    }

    @Override
    public Submitter convertToEntityAttribute(String dbData){
        try{
            if(dbData == null){
                return null;
            }else {
                return objectMapper.readValue(dbData, Submitter.class);
            }
        }catch (IOException ex){
            return null;
        }
    }
}
