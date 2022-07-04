package com.emaratech.platform.lookupsvc.store.api;

/**
 * Interface for the service which provides the contracts for
 * storing/fetching/updating/deleting the lookup data.
 */
public interface LookupStoreRepository {

    /**
     * Fetches the list of data as json string.
     *
     * @param redisKey the redisKey
     * @return json string
     */
    String findAll(String redisKey);

    /**
     * Creates the new entry in lookup data.
     *
     * @param redisKey the redisKey
     * @param data the data
     */
    void save(String redisKey, String data);

}
