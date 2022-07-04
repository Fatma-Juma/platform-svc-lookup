package com.emaratech.platform.lookupsvc.util;

import java.lang.reflect.Method;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.util.ReflectionUtils;

/**
 * This class provides the conversions methods.
 */
public class ConversionHelper {

    private final ModelMapper modelMapper;

    public ConversionHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static List<?> getList(String filePath, String entityName) throws Exception {
        Class clazz = Class.forName("com.emaratech.platform.lookupsvc.model." + entityName);
        List<?> list;

        ExcelToObjectMapper mapper = new ExcelToObjectMapper(filePath);
        list = mapper.map(clazz.getNestHost());
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(clazz);
        list.sort((o, t1) -> {
            return -1;
        });

        return list;

    }

}
