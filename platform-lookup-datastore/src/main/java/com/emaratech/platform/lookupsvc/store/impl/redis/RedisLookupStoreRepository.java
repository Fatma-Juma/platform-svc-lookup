package com.emaratech.platform.lookupsvc.store.impl.redis;

import javax.annotation.PostConstruct;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.emaratech.platform.lookupsvc.store.api.LookupStoreRepository;

/**
 * Redis specific implementation for the Lookup Repository.
 */
@Component
public class RedisLookupStoreRepository implements LookupStoreRepository {

    private RedisTemplate<String, Object> redisTemplate;

    /**
     * The hashOperations object for redis.
     */
    private HashOperations hashOperations;

    /**
     * Constructor overloading to inject the redisTemplate instance.
     *
     * @param redisTemplate the redisTemplate
     */
    public RedisLookupStoreRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Initializing the hashOperations from redisTemplate.
     */
    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    /** {@inheritDoc} */
    @Override
    public String findAll(String redisKey) {
        return String.valueOf(hashOperations.get(redisKey, redisKey));
    }

    /** {@inheritDoc} */
    @Override
    public void save(String redisKey, String data) {
        hashOperations.put(redisKey, redisKey, data);
    }
}
