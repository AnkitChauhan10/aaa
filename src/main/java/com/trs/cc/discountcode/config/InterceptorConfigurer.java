package com.trs.cc.discountcode.config;

import com.trs.cc.discountcode.interceptor.DiscountCodeAuthorizationInterceptor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {
 
	
	@Autowired
	DiscountCodeAuthorizationInterceptor discountCodeAuthorizationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(discountCodeAuthorizationInterceptor)
				.excludePathPatterns(
				"/swagger-ui.html",
				"/webjars/**",
				"/swagger-resources",
				"/v2/api-docs",
				"/configuration/security",
				"/configuration/ui",
				"/error"
		);
		log.info("Adding Login authentication interceptor");
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
