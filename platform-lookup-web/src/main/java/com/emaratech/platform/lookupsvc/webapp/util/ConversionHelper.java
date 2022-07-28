package com.emaratech.platform.lookupsvc.webapp.util;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.emaratech.platform.lookupsvc.model.Country;
import com.emaratech.platform.lookupsvc.util.ConversionUtils;
import com.emaratech.platform.lookupsvc.util.LookupMetaData;
import com.emaratech.platform.lookupsvc.webapp.model.LookupDTO;
import com.emaratech.platform.lookupsvc.webapp.model.LookupSubResponse;
import com.poiji.bind.Poiji;

/**
 * This class provides the conversions methods.
 */
@Component
public class ConversionHelper {

    private final LookupMetaData lookupMetaData;

    /**
     * Constructor overloading to inject the lookupMetaData.
     *
     * @param lookupMetaData the lookupMetaData
     */
    public ConversionHelper(LookupMetaData lookupMetaData) {
        this.lookupMetaData = lookupMetaData;
    }

    /**
     * Gets the sorted list.
     *
     * @param file the file
     * @param entityName the entityName
     * @return list of objects
     * @throws Exception if unable to mapped the list to target object.
     */
    public static List<?> getMapSortedList(File file, String entityName) throws Exception {
        Class clazz = Class.forName("com.emaratech.platform.lookupsvc.model." + entityName);
        List<?> list = Poiji.fromExcel(file, clazz);
        list.sort((o, t1) -> -1);
        return list;
    }

    /**
     * Builds the partial lookup response.
     *
     * @param sourceList the sourceList
     * @param entityName the entityName
     * @return list of objects
     */
    public List<LookupDTO> buildPartialLookupResponse(List<?> sourceList, String entityName) {

        return sourceList.stream().map(obj -> {
            String getterMethodName = lookupMetaData.getIdGetterForEntity(entityName);
            Long id = (Long) ConversionUtils
                    .getMethodValueByReflection(getterMethodName, obj);
            String nameEn = (String) ConversionUtils
                    .getMethodValueByReflection("get" + entityName + "NameEn", obj);
            String nameAr = (String) ConversionUtils
                    .getMethodValueByReflection("get" + entityName + "NameAr", obj);

            return new LookupDTO(id, nameEn, nameAr);
        }).collect(Collectors.toList());
    }

    /**
     * Builds the partial lookup response.
     *
     * @param sourceList the sourceList
     * @return list of objects
     */
    public List<LookupSubResponse> buildPartialLookupResponse(List<Country> sourceList) {

        return sourceList.stream().map(country -> new LookupDTO(country.getCountryId().longValue(),
                                                                country.getCountryNameEn(), country.getCountryNameAr(), country.getCountryCode()))
                .collect(Collectors.toList());

    }

    /**
     * Builds the response by code.
     *
     * @param sourceList the sourceList
     * @param entityName the entityName
     * @return list of object
     */
    public List<?> buildResponseByCode(List<?> sourceList,
                                       String entityName, String code) {

        return buildPartialLookupResponse(sourceList.stream().filter(obj -> {
            String getterMethodForCode = lookupMetaData.getMethodNameForDuplicationForEntity(entityName);
            if (getterMethodForCode.contains(",")) {
                getterMethodForCode = getterMethodForCode.split(",")[0];
            }
            String val = (String) ConversionUtils.getMethodValueByReflection(getterMethodForCode, obj);
            return val.equalsIgnoreCase(code.toUpperCase());
        }).collect(Collectors.toList()), entityName);
    }
}
