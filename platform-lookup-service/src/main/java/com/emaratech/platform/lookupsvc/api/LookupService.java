package com.emaratech.platform.lookupsvc.api;

import java.util.List;

import org.springframework.web.server.ResponseStatusException;

/**
 * Lookup service interface for fetching the lookups' data.
 */
public interface LookupService {

    /**
     * Fetches the list of data.
     *
     * @param entityName the entityName
     * @return the String data
     * @throws ResponseStatusException if unable to find the record
     */
    List<?> findAll(String entityName) throws ResponseStatusException;

    /**
     * Fetches the data by id.
     *
     * @param entityName the entityName
     * @param id the id
     * @return the String data
     * @throws ResponseStatusException if unable to find by id
     */
    Object findById(String entityName, Long id) throws ResponseStatusException;

    /**
     * Saves the new data to lookup list.
     *
     * @param className the className
     * @param data the data
     * @throws ResponseStatusException if unable to save the record
     */
    void save(String className, String data) throws ResponseStatusException;

    /**
     * Saves the new data into lookup list.
     *
     * @param pClassName the pClassName
     * @param subClassName the subClassName
     * @param data the data
     */
    void saveRelation(String pClassName, String subClassName, String data);

    /**
     * Saves the rdms lookupData into redis.
     *
     * @param entityName the the entityName
     * @param data the the data
     */
    void saveRdmsData(String entityName, String data);
}
