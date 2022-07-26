package com.emaratech.platform.lookupsvc.util;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

/**
 * This class provides the conversion methods.
 */
public class ConversionUtils {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Making the constructor private.
     */
    private ConversionUtils() {

    }

    /**
     * Gets the target entity class instance.
     *
     * @param className the className
     * @return the target entity class
     */
    public static Class getClassFromString(String className) {
        try {
            return Class.forName("com.emaratech.platform.lookupsvc.model." + className).getNestHost();
        } catch (ClassNotFoundException ex) {
            LOG.warn("Entity Name not found: {} ", className);
        }
        return null;
    }

    /**
     * Gets list of target Entity data from JsonArray.
     *
     * @param json the json
     * @param elementClass the elementClass
     * @param objectMapper the objectMapper
     * @param <T> the elementClass
     * @return List of target entity
     * @throws IOException if data parsing is failed
     */
    public static <T> List<T> jsonArrayToList(String json, Class<T> elementClass,
                                              ObjectMapper objectMapper)
        throws IOException {

        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
        return objectMapper.readValue(json, listType);
    }

    /**
     * Sets the primary Key.
     *
     * @param clazz the clazz
     * @param newIdValue the newIdValue
     * @param <T> the T
     * @param setterMethodName the setterMethodName
     * @return the target object
     */
    public static <T> T setId(Object clazz, Long newIdValue, String setterMethodName) {

        try {
            Method method = clazz.getClass().getDeclaredMethod(setterMethodName, BigDecimal.class);
            method.setAccessible(true);
            method.invoke(clazz, BigDecimal.valueOf(newIdValue));
        } catch (NoSuchMethodException e) {
            LOG.error("Error occurred during the class method fetched : {}", e.getMessage());
            clazz = null;
        } catch (InvocationTargetException e) {
            LOG.error("Error occurred during the class method invoke : {}", e.getMessage());
            clazz = null;
        } catch (IllegalAccessException e) {
            LOG.error("Error occurred during the class method access : {}", e.getMessage());
            clazz = null;
        }
        return (T) clazz;
    }

    /**
     * Gets the max id from Entity.
     *
     * @param listData the listData
     * @param getterMethodName the getterMethodName
     * @return Max id
     */
    public static Integer getMaxIdFromEntity(List<?> listData, String getterMethodName) {

        OptionalInt maxId = listData.stream().mapToInt(obj -> {
            BigDecimal cId = (BigDecimal) getMethodValueByReflection(getterMethodName, obj);
            return cId.intValue();
        }).max();
        return maxId.getAsInt();
    }

    /**
     * Checks duplicates in the list.
     *
     * @param listData the listData
     * @param valuesToCompare the valueToCompares
     * @param getterMethodsName the getterMethodsName
     * @return boolean true or false
     */
    public static boolean checkDuplicate(List<?> listData, String[] valuesToCompare, String[] getterMethodsName) {

        final AtomicBoolean isExist = new AtomicBoolean(false);
        listData.forEach(obj -> {
            String[] existingValues = new String[valuesToCompare.length];
            int index = 0;
            for (String methodName : getterMethodsName) {
                existingValues[index] = String.valueOf(getMethodValueByReflection(methodName, obj));
                index++;
            }
            index = 0;
            for (int iLoop = 0; iLoop < existingValues.length; iLoop++) {
                if (existingValues[iLoop].equals(valuesToCompare[iLoop])) {
                    index++;
                }
            }
            if (index == existingValues.length) {
                isExist.set(true);
                return;
            }

        });

        return isExist.get();
    }

    /**
     * Gets the value of target field.
     *
     * @param getterMethodName the getterMethodName
     * @param clazz the clazz
     * @return value from getter method
     */
    public static Object getMethodValueByReflection(String getterMethodName, Object clazz) {
        Object value = null;
        try {
            value = clazz.getClass()
                    .getMethod(getterMethodName).invoke(clazz);

        } catch (IllegalAccessException e) {
            LOG.error("Error occurred : IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            LOG.error("Error occurred : InvocationTargetException", e);
        } catch (NoSuchMethodException e) {
            LOG.error("Error occurred : NoSuchMethodException", e);
        }

        return value;
    }

    /**
     * Converts the json data into target entity class.
     *
     * @param clazz the clazz
     * @param data the data
     * @param objectMapper the objectMapper
     * @param <T> the T
     * @return the target object
     */
    public static <T> T convertJsonToTargetClass(Object clazz, String data, ObjectMapper objectMapper) {

        try {
            clazz = objectMapper.readValue(data, Class.forName(((Class) clazz).getName()));
        } catch (JsonProcessingException e) {
            LOG.error("Error occurred during the json transformation: {}", e.getMessage());
            clazz = null;

        } catch (ClassNotFoundException e) {
            LOG.error("Entity Name not found: {} ", e.getMessage());
            clazz = null;
        }
        return (T) clazz;
    }
}
