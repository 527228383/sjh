package com.sjh.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

/**
 * redis配置类
 * @author ex-sujh
 * @date 2018年6月27日17:39:31
 */
@Configuration
@Slf4j
public class RedisConfig extends CachingConfigurerSupport{
	
	/**
	 * 设置KEY的生成策略
	 */
	@Override
	@Bean
	public KeyGenerator keyGenerator() {
		
		return (target, method, params) -> {
			log.info("target:{},method:{},params:{}",target, method, params);
			StringBuilder sb = new StringBuilder();
			sb.append(target.getClass().getName());
			sb.append(".");
			sb.append(method.getName());
			for (Object obj : params) {
				sb.append(".");
				sb.append(obj.toString());
			}
			log.info("key:{}", sb.toString());
			return sb.toString();
		};
		
	}
	
	/**
	 * 设置redisTemplate的序列化方式
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<Object, Object>  template = new RedisTemplate<>();
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = this.getJackson2JsonRedisSerializer();
		template.setConnectionFactory(factory);
		
		template.setKeySerializer(stringRedisSerializer);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
	
	/**
	 * 设置redis默认过期时间
	 * 设置@Cacheable的序列化方式
	 * @return
	 */
	@Bean
	public RedisCacheConfiguration redisCacheConfiguration() {
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();

		defaultCacheConfig = defaultCacheConfig.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
											   .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(this.getJackson2JsonRedisSerializer())).entryTtl(Duration.ofDays(30));

		return defaultCacheConfig;
	}
	
	private Jackson2JsonRedisSerializer<Object> getJackson2JsonRedisSerializer() {
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		
		return jackson2JsonRedisSerializer;
	}
}
