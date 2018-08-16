package com.supcon.soft.iis.cim.jpa;

import com.supcon.soft.iis.cim.model.Submitter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class JpaConvertSubmitterWithJsonTest {
    private final JpaConvertSubmitterWithJson converter = new JpaConvertSubmitterWithJson();

    @Test
    public void convertToDatabaseColumn() {
        Submitter obj = new Submitter();

        String result = converter.convertToDatabaseColumn(obj);
        log.info(result);
    }

    @Test
    public void convertToEntityAttribute() {
        String str = "{\"userId\":\"0\",\"userName\":\"System\",\"personId\":null,\"personName\":null,\"departmentId\":null,\"departmentName\":null,\"positionId\":null,\"positionName\":null}";
        Submitter obj = converter.convertToEntityAttribute(str);
        log.info(obj.toString());
    }

    @Test
    public void convertToEntityAttributeWithNull(){
        Submitter obj = converter.convertToEntityAttribute(null);
        log.info(obj != null ? obj.toString(): null);
    }
}