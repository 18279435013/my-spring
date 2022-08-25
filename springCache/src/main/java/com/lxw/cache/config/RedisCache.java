package com.lxw.cache.config;

import org.apache.catalina.webresources.Cache;
import org.apache.catalina.webresources.StandardRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;

public class RedisCache extends AbstractValueAdaptingCache {

    public static Set<String> keySet = new HashSet<>(1024);

    private final String name = "user";

    private RedisTemplate<Object,Object> redisTemplate;

    public RedisCache() {
        super(false);
    }

    /**
     * Create an {@code AbstractValueAdaptingCache} with the given setting.
     *
     * @param allowNullValues whether to allow for {@code null} values
     */
    protected RedisCache(boolean allowNullValues) {
        super(allowNullValues);
    }

    @Override
    protected Object lookup(Object key) {
        return redisTemplate.opsForHash().get(this.name,key);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        HashOperations<Object, Object, Object> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(this.name);
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        HashOperations<Object, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(this.name,key,value);
    }

    @Override
    public void evict(Object key) {
        HashOperations<Object, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(this.name,key);
    }

    @Override
    public void clear() {
        redisTemplate.delete(this.name);
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
