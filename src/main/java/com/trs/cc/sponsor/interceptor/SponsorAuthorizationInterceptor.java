package com.trs.cc.sponsor.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trs.cc.notification.constant.MessageConstants;
import com.trs.cc.notification.decorator.RequestSession;
import com.trs.cc.notification.decorator.Response;
import com.trs.cc.notification.model.JWTUser;
import com.trs.cc.notification.services.NotificationAPIService;
import com.trs.cc.notification.utils.CustomHTTPHeaders;
import com.trs.cc.notification.utils.JwtTokenUtil;
import com.trs.cc.notification.utils.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

/**
 * login intercepter DO NOT CHANGE any implementation here, if needed extend the
 * AuthenticationIntercepter and override the required methods.
 * 
 * @author TRS
 *
 */
@Component
public class SponsorAuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
    NotificationAPIService notificationAPIService;

	@Autowired
	JwtTokenUtil tokenUtil;

	@Autowired
	RequestSession requestSession;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof HttpRequestHandler){
			return true;
		}


		HandlerMethod method = (HandlerMethod) handler;
		RequestMapping rm = method.getMethodAnnotation(RequestMapping.class);
		logger.info("Session Id (In Interceptor) :"+requestSession.getR());
		// If This is Resource Request then always return true

		// IF ANONYMOUS Role then Pass the role
		if(notificationAPIService.hasAccess(Collections.singletonList(Roles.ANONYMOUS.toString()),method.getMethod().getName())){
			return true;
		}

		String jwtToken = request.getHeader(CustomHTTPHeaders.TOKEN.toString());
		if(jwtToken == null){
			logger.error("Authentication not present in the request");
			Response errorResponse = new Response(HttpStatus.UNAUTHORIZED,
					MessageConstants.AUTHORIZATION_IS_NOT_PRESENT_IN_REQUEST,
					MessageConstants.AUTHORIZATION_IS_NOT_PRESENT_IN_REQUEST);
			// Token is required if api is not ANONYMOUS
			sendJSONResponse(errorResponse, response, HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		JWTUser user;
		try{
			user = tokenUtil.getJwtUserFromToken(jwtToken);
			if(!notificationAPIService.hasAccess(user.getRole(),method.getMethod().getName())){
				logger.error("Role is not allowed");
				Response errorResponse = new Response(HttpStatus.FORBIDDEN,
						MessageConstants.ROLE_IS_NOT_ALLOWED, MessageConstants.ROLE_IS_NOT_ALLOWED);
				// if Role is not allowed
				sendJSONResponse(errorResponse, response, HttpServletResponse.SC_FORBIDDEN);
				return false;
			}

		}catch (Exception e){
			logger.error("Invalid Token Signature!! ");
			Response errorResponse = new Response(HttpStatus.UNAUTHORIZED,
					MessageConstants.INVALID_TOKEN_SIGNATURE, MessageConstants.INVALID_TOKEN_SIGNATURE);
			// if Token is invalid or signature is invalid
			sendJSONResponse(errorResponse, response, HttpServletResponse.SC_UNAUTHORIZED);
			return false;

		}
		requestSession.setJwtUser(user);
		return true;


	}


	public void sendJSONResponse(Object modal,HttpServletResponse response,int status) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		response.setStatus(status);
		response.setContentType("application/json");
		response.getWriter().write(mapper.writeValueAsString(modal));
	}

	/**
	 *
	 * @param base64Credentials
	 * @return
	 */
	private String[] decodeAuthorizationHeader(String base64Credentials) {
		byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
		String credentials = new String(credDecoded, StandardCharsets.UTF_8);
		// credentials = username:password
		final String[] values = credentials.split(":", 3);
		return values;

	}
}
