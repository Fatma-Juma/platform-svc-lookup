package com.emaratech.platform.lookupsvc.config.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.resource.ClientResources;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * These are the redis configuration for icm cache.
 */
@Configuration
@PropertySource("classpath:application-redis.properties")
public class RedisConfiguration {

    private final RedisProperties redisProperties;

    @Value("#{new Boolean('${redis.standalone.enabled:false}')}")
    private boolean isRedisStandAloneEnabled;


    /**
     * Copy constructor.
     *
     * @param redisProperties
     */
    public RedisConfiguration(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    /**
     * This is connection factory for redis.
     *
     * @return lettuceConnectionFactory
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        if (!isRedisStandAloneEnabled) {
            RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                    .master(redisProperties.getSentinel().getMaster());
            redisProperties.getSentinel().getNodes()
                    .forEach(s -> sentinelConfig.addSentinel(new RedisNode(redisProperties.getHost(), redisProperties.getPort())));
            return new LettuceConnectionFactory(sentinelConfig);
        } else {
            return new LettuceConnectionFactory();
        }
    }

    /**
     * This method create the redis template with custom configuration.
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        return redisTemplate;

    }

    @Bean
    LettucePoolingClientConfiguration lettucePoolConfig(ClientOptions options, ClientResources dcr){
        return LettucePoolingClientConfiguration.builder()
                .poolConfig(new GenericObjectPoolConfig())
                .clientOptions(options)
                .clientResources(dcr)
                .build();
    }

    @Bean
    public ClientOptions clientOptions(){
        return ClientOptions.builder()
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                .autoReconnect(true)
                .build();
    }
}
