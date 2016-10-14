package com.mackie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.mackie.domain.UserDO;

@Configuration
public class WebConfig {

    @Bean
    @SuppressWarnings("rawtypes")
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }

    @Bean
    @SuppressWarnings("rawtypes")
    public RedisTemplate<String, UserDO> redisTemplate(RedisConnectionFactory factory, RedisSerializer fastJson2JsonRedisSerializer) {
    	RedisTemplate<String, UserDO> template = new RedisTemplate<String, UserDO>();
    	
    	template.setConnectionFactory(factory);
    	
    	template.setStringSerializer(fastJson2JsonRedisSerializer);

        template.setValueSerializer(fastJson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }
}
