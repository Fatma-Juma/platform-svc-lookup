package com.emaratech.platform.lookupsvc.impl;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.emaratech.platform.lookupsvc.api.InvalidDataException;
import com.emaratech.platform.lookupsvc.api.LookupService;
import com.emaratech.platform.lookupsvc.store.api.LookupStoreRepository;
import com.emaratech.platform.lookupsvc.util.ConversionUtils;
import com.emaratech.platform.lookupsvc.util.Converter;
import com.emaratech.platform.lookupsvc.util.LookupMetaData;
import com.emaratech.platform.lookupsvc.util.ValidationUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Standard implementation for the lookup service.
 */
@Service
public class StandardLookupService implements LookupService {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final LookupStoreRepository lookupStoreRepository;

    private final Converter modelConverter;

    private final ObjectMapper objectMapper;

    private final LookupMetaData lookupMetaData;

    /**
     * Constructor overloading to inject the lookupStoreRepository.
     *
     * @param lookupStoreRepository the lookupStoreRepository
     * @param modelConverter the modelConverter
     * @param objectMapper the objectMapper
     * @param lookupMetaData the lookupMetaData
     */
    public StandardLookupService(LookupStoreRepository lookupStoreRepository,
            Converter modelConverter, ObjectMapper objectMapper, LookupMetaData lookupMetaData) {
        this.lookupStoreRepository = lookupStoreRepository;
        this.modelConverter = modelConverter;
        this.objectMapper = objectMapper;
        this.lookupMetaData = lookupMetaData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<?> findAll(String entityName) throws ResponseStatusException {
        LOG.info("Fetching the all records from: {} ", entityName);
        Class clazz = ConversionUtils.getClassFromString(entityName);
        if (Objects.isNull(clazz)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entity Name not found: [" + entityName + "]");
        }
        List<?> listData;
        try {
            listData = ConversionUtils.jsonArrayToList(lookupStoreRepository.findAll(entityName), clazz, objectMapper);
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return listData;
    }

    /** {@inheritDoc} */
    @Override
    public List<?> findById(String entityName, Long id) throws ResponseStatusException {
        try {
            List<?> listData = getExistingData(entityName);
            if (!CollectionUtils.isEmpty(listData)) {
                String getterMethodName = lookupMetaData.getIdGetterForEntity(entityName);
                listData = listData.stream()
                        .filter(obj -> {
                            BigDecimal targetId = (BigDecimal) ConversionUtils.getMethodValueByReflection(getterMethodName, obj);
                            if (targetId.longValue() == id) {
                                return true;
                            } else {
                                return false;
                            }

                        }).collect(Collectors.toList());

                return listData;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, " No lookup data found for [" + entityName + " ] by [" + id + "]");
            }
        } catch (InvalidDataException ex) {
            LOG.error("Error during the processing of method findById{}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(String className, String data) throws ResponseStatusException {
        LOG.info("Saving the lookup data : {},to entityName: {}", data, className);
        try {
            lookupStoreRepository.save(className, createOrAppendData(className, data));
        } catch (InvalidDataException | ConstraintViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        LOG.info("Saved the lookup data successfully.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveRelation(String pClassName, String subClassName, String data) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveRdmsData(String entityName, String data) {
        lookupStoreRepository.save(entityName, data);
    }

    /**
     * Creates or appends the data into target entity.
     *
     * @param className the className
     * @param data the data
     * @return the list of data
     * @throws InvalidDataException if data is not valid
     * @throws ConstraintViolationException if data have violations
     */
    private String createOrAppendData(String className, String data) throws InvalidDataException,
        ConstraintViolationException {
        String requestedData;
        Class clazz = ConversionUtils.getClassFromString(className);
        if (Objects.isNull(clazz)) {
            throw new InvalidDataException("EntityName is not valid: [" + className + "]");
        }
        List<Object> listData = getExistingData(className);
        String setterMethodName = lookupMetaData.getIdSetterForEntity(className);
        if (!CollectionUtils.isEmpty(listData)) {
            LOG.info("Appending the new record..");
            try {
                String getterMethodName = lookupMetaData.getIdGetterForEntity(className);
                // setting up the id value // list size + 1 would be new id.
                Long newId = ConversionUtils.getMaxIdFromEntity(listData, getterMethodName) + 1L;

                // Start objects conversions.
                Object updatedObject = ConversionUtils.setId(clazz, data, newId, objectMapper, setterMethodName);
                if (Objects.isNull(updatedObject)) {
                    LOG.error("Error occurred during setting the new Id.");
                    throw new InvalidDataException("UnExpected Error comes during setting up the new Id");
                }

                Set violations = ValidationUtils.validate(updatedObject);
                if (!CollectionUtils.isEmpty(violations)) {
                    throw new ConstraintViolationException("Data validation errors.", violations);
                }
                // validation- duplicate
                String methodName = lookupMetaData.getMethodNameForDuplicationForEntity(className);
                String value = (String) ConversionUtils.getMethodValueByReflection(methodName, updatedObject);
                if (!CollectionUtils.isEmpty(ConversionUtils.checkDuplicate(listData, value, methodName))) {
                    String fieldName = methodName.substring(3);
                    throw new InvalidDataException("[" + fieldName + "] shouldn't be duplicate.");
                }

                listData.add(updatedObject);
                requestedData = objectMapper.writeValueAsString(listData);
            } catch (JsonProcessingException ex) {
                LOG.error("Error occurred during the json transformation: {}", ex.getMessage());
                throw new InvalidDataException("Data is not valid.", ex);
            }
        } else {
            LOG.info("Saving first record and data is {}", data);
            // setting up the id value //Always 1 for first record.
            Object updatedObject = ConversionUtils.setId(clazz, data, 1L, objectMapper, setterMethodName);
            try {
                // Converting the updated entity to json.
                requestedData = objectMapper.writeValueAsString(modelConverter.mapEntityToDTO(updatedObject, clazz));
            } catch (JsonProcessingException ex) {
                LOG.error("Error occurred during the json transformation: {}", ex.getMessage());
                throw new InvalidDataException("Data is not valid.", ex);
            }
        }
        return requestedData;
    }

    /**
     * Gets the entity existing data list.
     * 
     * @param entityName the entityName
     * @return List of target entity
     * @throws InvalidDataException if entity is not found or data parsing is
     *             failed
     */

    private List getExistingData(String entityName) throws InvalidDataException {

        // Constructing the target EntityObject
        Class clazz = ConversionUtils.getClassFromString(entityName);
        List<?> listData;
        if (Objects.isNull(clazz)) {
            throw new InvalidDataException("EntityName is not valid: [" + entityName + "]");
        }
        try {
            String data = lookupStoreRepository.findAll(entityName);
            listData = ConversionUtils.jsonArrayToList(data, clazz, objectMapper);
        } catch (IOException e) {
            throw new InvalidDataException("Exception occurred during jsonArrayToLis method processing.");
        }
        return listData;
    }
}
