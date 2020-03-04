package com.trs.cc.news.decorator;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import static com.trs.cc.news.constant.ResponseConstant.*;

@JsonRootName ( value = "status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	
	HttpStatus code;
	String status;
	String description;

	public static Response getOkResponse(){
		return new Response(HttpStatus.OK, OK, OK_DESCRIPTION);
	}

	public static Response getSuccessResponse() {
		return new Response(HttpStatus.OK, SUCCESS, OK_DESCRIPTION);
	}

	public static Response getCreatedResponse(){
		return new Response(HttpStatus.CREATED, SUCCESS, CREATED_DESCRIPTION);
	}

	public static Response getUpdatedResponse(){
		return new Response(HttpStatus.OK, UPDATED, UPDATE_DESCRIPTION);
	}

	public static Response getDeletedResponse(){
		return new Response(HttpStatus.OK, DELETED, DELETED_DESCRIPTION);
	}

	public static Response getInvalidRequestResponse(){
		return new Response(HttpStatus.BAD_REQUEST, ERROR, INVALID_REQUEST_DESCRIPTION);
	}

	public static Response getInvalidDataResponse(){
		return new Response(HttpStatus.BAD_REQUEST, ERROR, INVALID_DATA_DESCRIPTION);
	}

	public static Response getNotFoundResponse(){
		return new Response(HttpStatus.BAD_REQUEST, ERROR, NOT_FOUND_DESCRIPTION);
	}

	public static Response getErrorResponse(String message, HttpStatus statusCode) {
		return new Response(statusCode, ERROR, message);
	}
	public static Response getSuccessResponse(String message, HttpStatus statusCode) {
		return new Response(statusCode, SUCCESS, message);
	}

}
