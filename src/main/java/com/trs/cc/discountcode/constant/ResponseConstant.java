package com.trs.cc.discountcode.constant;

import com.trs.cc.discountcode.decorator.Response;
import org.springframework.http.HttpStatus;

public class ResponseConstant {
    //Response description
    public static final String DELETED_DESCRIPTION = "Deleted Successfully";
    public static final String CREATED_DESCRIPTION = "Created Successfully";
    public static final String UPDATE_DESCRIPTION = "Updated Successfully";
    public static final String INVALID_REQUEST_DESCRIPTION = "Invalid Request";
    public static final String INVALID_DATA_DESCRIPTION = "Invalid Data";
    public static final String NOT_FOUND_DESCRIPTION = "No Record Found";
    public static final String OK_DESCRIPTION = "Success";

    //Response status
    public static final String DELETED = "DELETED";
    public static final String SUCCESS = "SUCCESS";
    public static final String VERIFY = "VERIFY";
    public static final String UPDATED = "UPDATED";
    public static final String ERROR = "ERROR";
    public static final String FAIL = "Fail";
    public static final String OK = "OK";

    public static final Response OK_RESPONSE = new Response(HttpStatus.OK, OK, OK_DESCRIPTION);
    public static final Response UPDATE_RESPONSE = new Response(HttpStatus.OK, UPDATED, UPDATE_DESCRIPTION);
    public static final Response DELETE_RESPONSE = new Response(HttpStatus.OK, DELETED, DELETED_DESCRIPTION);
}
