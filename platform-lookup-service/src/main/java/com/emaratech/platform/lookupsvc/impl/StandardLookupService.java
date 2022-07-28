package com.emaratech.platform.lookupsvc.impl;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import com.emaratech.platform.lookupsvc.api.InvalidDataException;
import com.emaratech.platform.lookupsvc.api.LookupService;
import com.emaratech.platform.lookupsvc.store.api.LookupStoreRepository;
import com.emaratech.platform.lookupsvc.util.ConversionUtils;
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

    private final ObjectMapper objectMapper;

    private final LookupMetaData lookupMetaData;

    /**
     * Constructor overloading to inject the lookupStoreRepository.
     *
     * @param lookupStoreRepository the lookupStoreRepository
     * @param objectMapper the objectMapper
     * @param lookupMetaData the lookupMetaData
     */
    public StandardLookupService(LookupStoreRepository lookupStoreRepository,
            ObjectMapper objectMapper, LookupMetaData lookupMetaData) {
        this.lookupStoreRepository = lookupStoreRepository;
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
            LOG.error("Exception occurred {} :", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        if (CollectionUtils.isEmpty(listData)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data is found against:  " + entityName);
        }
        return listData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<?> findById(String entityName, Long id) throws ResponseStatusException {
        try {
            Class clazz = ConversionUtils.getClassFromString(entityName);
            if (Objects.isNull(clazz)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Entity Name not found: [" + entityName + "]");
            }
            List<?> listData = getExistingData(clazz);
            if (!CollectionUtils.isEmpty(listData)) {
                String getterMethodName = lookupMetaData.getIdGetterForEntity(entityName);
                listData = listData.stream()
                        .filter(obj -> {
                            Long targetId = (Long) ConversionUtils.getMethodValueByReflection(getterMethodName, obj);
                            return targetId == id;

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
            lookupStoreRepository.save(className, insertLookup(className, data));
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
    public void saveRdmsData(String entityName, String data) {

        String value = lookupStoreRepository
                .findDataImportDetailsByEntity("lookup:entity:" + entityName);
        if (!StringUtils.hasLength(value)) {
            // saving the data into redis
            lookupStoreRepository.save(entityName, data);
            // saving the importDetails.
            lookupStoreRepository.saveImportDetails("lookup:import:tables",
                                                    "entityName= " + entityName + ":importDateTime=" + LocalDateTime.now());
            // Saving the importDetails for individual key.
            lookupStoreRepository.save("lookup:entity:" + entityName, LocalDateTime.now().toString());
        } else {
            LOG.warn("Data is already imported into Redis for the entity {} ", entityName);
        }
    }

    @Override
    public List<?> getImportDetails() {
        return lookupStoreRepository.findAllDataImportDetails();
    }

    /**
     * Inserts the lookups.
     *
     * @param className the className
     * @param data the data
     * @return the json data
     * @throws InvalidDataException if unable to process the json data
     */
    private String insertLookup(String className, String data) throws InvalidDataException {
        // Getting the target class
        Class clazz = ConversionUtils.getClassFromString(className);
        // checks class is not null or not.
        if (Objects.isNull(clazz)) {
            throw new InvalidDataException("EntityName is not valid: [" + className + "]");
        }
        boolean isExist = true;
        Long newId = 1L;
        // Getting the existing data
        List<Object> listData = getExistingData(clazz);
        if (CollectionUtils.isEmpty(listData)) {
            isExist = false;
        } else {
            String getterMethodName = lookupMetaData.getIdGetterForEntity(clazz.getSimpleName());
            newId = ConversionUtils.getMaxIdFromEntity(listData, getterMethodName) + 1L;
        }
        List<Object> objectList;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            objectList = ConversionUtils.jsonArrayToList(data, clazz, objectMapper);
            for (Object object : objectList) {
                stringBuilder.append(createOrAppendData(clazz,
                                                        objectMapper.writeValueAsString(object), listData, newId, isExist));
                newId++;
            }
        } catch (IOException e) {
            throw new InvalidDataException("Data is not valid");
        }
        return stringBuilder.toString();
    }

    /**
     * Creates or appends the data into target entity.
     *
     * @param clazz the clazz
     * @param data the data
     * @param listData the listData
     * @param newId the newId
     * @param isExist the isExist
     * @return the list of data
     * @throws InvalidDataException if data is not valid
     * @throws ConstraintViolationException if data have violations
     */
    private String createOrAppendData(Class clazz, String data, List listData, Long newId, boolean isExist) throws InvalidDataException,
        ConstraintViolationException {
        String requestedData;
        Object updatedObject;

        // convert json to target class object.
        updatedObject = ConversionUtils.convertJsonToTargetClass(clazz, data, objectMapper);
        if (Objects.isNull(updatedObject)) {
            throw new InvalidDataException("Invalid request data is passed.");
        }

        // validates the data.
        Set violations = ValidationUtils.validate(updatedObject);
        if (!CollectionUtils.isEmpty(violations)) {
            throw new ConstraintViolationException("Data validation errors.", violations);
        }
        // Getting the target class setter method for primary id
        String setterMethodName = lookupMetaData.getIdSetterForEntity(clazz.getSimpleName());
        try {
            if (isExist) {
                LOG.info("Appending the new record..");
                // validation- duplicate
                String methodName = lookupMetaData.getMethodNameForDuplicationForEntity(clazz.getSimpleName());
                String[] values = new String[1];
                String[] methodNames = new String[] {methodName};
                if (methodName.contains(",")) {
                    methodNames = methodName.split(",");
                    values = new String[methodNames.length];
                }
                int index = 0;
                for (String mName : methodNames) {
                    values[index] = String.valueOf(ConversionUtils.getMethodValueByReflection(mName, updatedObject));
                    index++;
                }

                if ((ConversionUtils.checkDuplicate(listData, values, methodNames))) {
                    String fieldName = methodName.replaceAll("get", "");
                    throw new InvalidDataException("[" + fieldName + "] already exists.");
                }
            }
            // setting up the id value // list size + 1L or 1L would be new id.
            updatedObject = ConversionUtils.setId(updatedObject, newId, setterMethodName);
            if (Objects.isNull(updatedObject)) {
                LOG.error("Error occurred during setting the new Id.");
                throw new InvalidDataException("UnExpected Error comes during setting up the new Id");
            }
            listData.add(updatedObject);
            requestedData = objectMapper.writeValueAsString(listData);

        } catch (JsonProcessingException ex) {
            LOG.error("Error occurred during the json transformation: {}", ex.getMessage());
            throw new InvalidDataException("Data is not valid.", ex);
        }
        return requestedData;
    }

    /**
     * Gets the entity existing data list.
     *
     * @param clazz the clazz
     * @return List of target entity
     * @throws InvalidDataException if entity is not found or data parsing is
     *             failed
     */

    private List<Object> getExistingData(Class clazz) throws InvalidDataException {

        List<Object> listData;

        try {
            String data = lookupStoreRepository.findAll(clazz.getSimpleName());
            listData = ConversionUtils.jsonArrayToList(data, clazz, objectMapper);
        } catch (IOException e) {
            throw new InvalidDataException("Exception occurred during jsonArrayToList method processing.");
        }
        return listData == null ? new ArrayList<>() : listData;
    }
}
