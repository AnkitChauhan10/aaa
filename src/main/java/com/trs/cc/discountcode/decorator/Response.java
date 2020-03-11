package com.trs.cc.discountcode.decorator;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.trs.cc.discountcode.services.ResponseManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import static com.trs.cc.discountcode.constant.ResponseConstant.*;

@JsonRootName ( value = "status")
@Getter
@Setter
public class Response{

	HttpStatus code;
	String status;
	String description;

	public Response(HttpStatus code, String status, String description) {
		this.code = code;
		this.status = status;
		this.description = description;
	}

	public static Response getOkResponse(){
		return new Response(HttpStatus.OK, OK, OK_DESCRIPTION);
	}

	public static Response getSuccessResponse() {
		return ResponseManager.getResponse(HttpStatus.OK, SUCCESS, OK_DESCRIPTION);
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

	public static Response getNotFoundResponse(String message){
		return new Response(HttpStatus.BAD_REQUEST, ERROR, message);
	}

	public static Response getErrorResponse(String message, HttpStatus statusCode) {
		return new Response(statusCode, ERROR, message);
	}

	public static Response getSuccessResponse(String message, HttpStatus statusCode) {
		return new Response(statusCode, SUCCESS, message);
	}

}
