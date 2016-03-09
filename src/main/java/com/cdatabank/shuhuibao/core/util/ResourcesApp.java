package com.cdatabank.shuhuibao.core.util;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class ResourcesApp extends ResourceConfig{

	public ResourcesApp() {
		register(RequestContextFilter.class);
		
		register(JacksonFeature.class);	
		
		register(LoggingResponseFilter.class);
		
		packages("com.cdatabank.shuhuibao.app.android",
				"com.cdatabank.shuhuibao.common",
				"com.cdatabank.shuhuibao.manage",
				"com.cdatabank.shuhuibao.net");
	}

	
}
