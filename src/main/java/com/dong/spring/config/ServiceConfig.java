package com.dong.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Component
public class ServiceConfig {

	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {

		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		requestMappingHandlerAdapter.getMessageConverters().add(jackson2HttpMessageConverter());

		return requestMappingHandlerAdapter;
	}

	@Bean
	public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {

		return new MappingJackson2HttpMessageConverter();
	}

}
