package com.ringo.togglz_server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.redis.RedisStateRepository;

import com.ringo.togglz_server.model.BarFeatures;
import com.ringo.togglz_server.model.FooFeatures;

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
        //If concerned about amount of DB requets, this Repo can be cached
        //https://www.togglz.org/documentation/repositories#CachingStateRepository
        return new RedisStateRepository.Builder()
                .keyPrefix(REDIS_KEY_PREFIX)
                .jedisPool(pool)
                .build();
    }

    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(FooFeatures.class, BarFeatures.class);
    }
}
