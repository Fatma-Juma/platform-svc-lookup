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
     * Constructs the map for class getter and setter.
     */
    @PostConstruct
    private void init() {
        classMetaDataMap = new HashMap<>();
        classMetaDataMap.put("Country-Id-Setter", "setCountryId");
        classMetaDataMap.put("Country-Id-Getter", "getCountryId");
        classMetaDataMap.put("Country-Dup-Field", "getCountryCode");

        classMetaDataMap.put("Emirate-Id-Setter", "setEmirateId");
        classMetaDataMap.put("Emirate-Id-Getter", "getEmirateId");
        classMetaDataMap.put("Emirate-Dup-Field", "getCode");

        classMetaDataMap.put("Area-Id-Setter", "setAreaId");
        classMetaDataMap.put("Area-Id-Getter", "getAreaId");
        classMetaDataMap.put("Area-Dup-Field", "getAreaEn");

        classMetaDataMap.put("City-Id-Setter", "setCityId");
        classMetaDataMap.put("City-Id-Getter", "getCityId");
        classMetaDataMap.put("City-Dup-Field", "getCityNameEn");

        classMetaDataMap.put("Faith-Id-Setter", "setFaithId");
        classMetaDataMap.put("Faith-Id-Getter", "getFaithId");
        classMetaDataMap.put("Faith-Dup-Field", "getFaithNameEn");

        classMetaDataMap.put("VisaType-Id-Setter", "setVisaTypeId");
        classMetaDataMap.put("VisaType-Id-Getter", "getVisaTypeId");

        classMetaDataMap.put("Destination-Id-Setter", "setDestinationId");
        classMetaDataMap.put("Destination-Id-Getter", "getDestinationId");
        classMetaDataMap.put("Destination-Dup-Field", "getDestinationCode");

        classMetaDataMap.put("AlternateCountryCode-Id-Setter", "setAlternateCountryCodeId");
        classMetaDataMap.put("AlternateCountryCode-Id-Getter", "getAlternateCountryCodeId");
        classMetaDataMap.put("AlternateCountryCode-Dup-Field", "getAlternateCountryCode");

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
