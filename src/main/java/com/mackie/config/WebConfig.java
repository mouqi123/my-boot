package com.mackie.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	@ConditionalOnClass({JSON.class})
	public HttpMessageConverter<?> fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
		
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		
		return fastConverter;
	}
}
