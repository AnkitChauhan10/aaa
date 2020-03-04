package com.trs.cc.news.config;

import com.trs.cc.news.interceptor.SponsorAuthorizationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configure Interceptors on application startup. each interceptor is added in the order of first come first served.
 * Authentication interceptor needs to be added by default.
 * {code}
 * 	AuthenticationInterceptor
 * {code}
 * @author TRS
 *
 */

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {
 
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	SponsorAuthorizationInterceptor sponsorAuthorizationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sponsorAuthorizationInterceptor).excludePathPatterns(
				"/swagger-ui.html",
				"/webjars/**",
				"/swagger-resources",
				"/v2/api-docs",
				"/configuration/security",
				"/configuration/ui",
				"/error"
		);
		logger.info("Adding Login authentication interceptor");
		WebMvcConfigurer.super.addInterceptors(registry);
	}


	/**
	 * Add Cross origin mapping at global Scale
	 * @param registry
	 */

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

}
