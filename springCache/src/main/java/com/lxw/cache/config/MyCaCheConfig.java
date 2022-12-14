package com.lxw.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.lxw.config.MyRedisSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.config.CacheManagementConfigUtils;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Configuration
public class MyCaCheConfig extends CachingConfigurerSupport{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Nullable
    public CacheManager cacheManager() {
        return getRedisCacheManager(redisTemplate);
    }

    @Bean
    public CacheManager getRedisCacheManager(RedisTemplate redisTemplate){

        Jackson2JsonRedisSerializer<Object> serializer2 = new Jackson2JsonRedisSerializer<Object>(Object.class);
        //εΊεε
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer2.setObjectMapper(objectMapper);


        //θͺε?δΉε€η
//        RedisTemplate my = new RedisTemplate<>();
//        my.setConnectionFactory(redisTemplate.getConnectionFactory());
//        // δ½Ώη¨StringRedisSerializerζ₯εΊεεεεεΊεεredisηkeyεΌ
//        MyRedisSerializer<Object> serializer = new MyRedisSerializer<>(Object.class);
//        my.setHashValueSerializer(serializer2);
//        my.setHashKeySerializer(serializer);
//        my.setKeySerializer(new StringRedisSerializer());
//        my.afterPropertiesSet();
//        MyRedisCacheManager cacheManager = new MyRedisCacheManager();
//        cacheManager.setTemplate(my);


        //redis θͺεΈ¦ε€ηε¨
        RedisCacheManager manager = RedisCacheManager.RedisCacheManagerBuilder
            // Redis θΏζ₯ε·₯ε
            .fromConnectionFactory(redisTemplate.getConnectionFactory())
            .cacheDefaults(getRedisCacheConfiguration(100))
            //ζε?ζζζ
            .withCacheConfiguration("user",getRedisCacheConfiguration(1011))
            // ιη½?εζ­₯δΏ?ζΉζε ι€ put/evict
            .transactionAware()
            .build();

        return manager;
    }


    //θ·ειη½?δΏ‘ζ―
    public RedisCacheConfiguration getRedisCacheConfiguration( long seconds){
        Jackson2JsonRedisSerializer<Object> serializer2 = new Jackson2JsonRedisSerializer<Object>(Object.class);
        //εΊεε
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer2.setObjectMapper(objectMapper);

        return  RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer2))
            // δΈηΌε­null
            .disableCachingNullValues()
            // ηΌε­ζ°ζ?δΏε­1ε°ζΆ
            .entryTtl(Duration.ofSeconds(seconds, 100));
    }
}
