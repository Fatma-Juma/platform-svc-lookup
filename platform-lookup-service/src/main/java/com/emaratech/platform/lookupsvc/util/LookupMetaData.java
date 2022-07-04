package com.emaratech.platform.lookupsvc.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * Entity Lookup meta data.
 */
@Component
public class LookupMetaData {

    private Map<String, String> classMetaDataMap;

    /**
     *
     */
    @PostConstruct
    private void init() {
        classMetaDataMap = new HashMap<>();
        classMetaDataMap.put("Country-Id-Setter", "setCountryId");
        classMetaDataMap.put("Country-Id-Getter", "getCountryId");
        classMetaDataMap.put("Country-Dup-Field", "getCountryCode");
    }

    /**
     * Gets the setter method for entity.
     *
     * @param entityName the entityName
     * @return name of setter method
     */
    public String getIdSetterForEntity(String entityName) {
        return classMetaDataMap.get(entityName + "-Id-Setter");
    }

    /**
     * Gets the getter method name for entity.
     *
     * @param entityName the entityName
     * @return name of getter method
     */
    public String getIdGetterForEntity(String entityName) {
        return classMetaDataMap.get(entityName + "-Id-Getter");
    }

    /**
     * Gets the method name for duplication check.
     *
     * @param entityName the entityName
     * @return name of duplication check method name
     */
    public String getMethodNameForDuplicationForEntity(String entityName) {
        return classMetaDataMap.get(entityName + "-Dup-Field");
    }
}
