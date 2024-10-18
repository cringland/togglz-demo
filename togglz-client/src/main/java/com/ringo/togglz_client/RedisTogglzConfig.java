package com.ringo.togglz_client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.redis.RedisStateRepository;

import com.ringo.togglz_client.model.FooFeatures;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.util.Pool;

@Configuration
public class RedisTogglzConfig {
    private static final String REDIS_KEY_PREFIX = "feature.switches.";



    @Bean
    public Pool<Jedis> jedisConnectionFactory() {
        return new JedisPool();
    }

    @Bean
    public StateRepository redisStateRepository(final Pool<Jedis> pool) {
        //Might be worth extending RedisStateRepository to disable setting.
        return new RedisStateRepository.Builder()
                .keyPrefix(REDIS_KEY_PREFIX)
                .jedisPool(pool)
                .build();
    }

    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(FooFeatures.class);
    }
}
