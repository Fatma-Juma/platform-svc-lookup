package com.emaratech.platform.lookupsvc.webapp.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.emaratech.platform.lookupsvc.util.ConversionUtils;
import com.emaratech.platform.lookupsvc.util.LookupMetaData;
import com.emaratech.platform.lookupsvc.webapp.model.LookupDTO;
import com.emaratech.platform.lookupsvc.webapp.model.LookupSubResponse;
import org.springframework.stereotype.Component;

import com.emaratech.platform.lookupsvc.model.Country;

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
     * @param filePath the filePath
     * @param entityName the entityName
     * @return list of objects
     * @throws Exception if unable to mapped the list to target object.
     */
    public static List<?> getMapSortedList(String filePath, String entityName) throws Exception {
        Class clazz = Class.forName("com.emaratech.platform.lookupsvc.model." + entityName);
        List<?> list;
        ExcelToObjectMapper mapper = new ExcelToObjectMapper(filePath);
        list = mapper.map(clazz.getNestHost());
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
            Long id = ((BigDecimal) ConversionUtils
                    .getMethodValueByReflection(getterMethodName, obj)).longValue();
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
            String val = (String) ConversionUtils.getMethodValueByReflection(getterMethodForCode, obj);
            return val.equalsIgnoreCase(code.toUpperCase());
        }).collect(Collectors.toList()), entityName);
    }
}
