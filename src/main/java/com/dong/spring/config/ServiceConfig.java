package com.dong.spring.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
//Define a customer source
//@PropertySource(value = { "classpath:application.properties", "classpath:sql.properties" })
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

	@Bean
	public static PropertyPlaceholderConfigurer properties() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[] 
				{ new ClassPathResource("application.properties"),
				  new ClassPathResource("sql.properties")  };
		ppc.setLocations(resources);
		ppc.setIgnoreUnresolvablePlaceholders(true);
		return ppc;
	}

}
