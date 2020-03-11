package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.decorator.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseManagerImpl implements ResponseManager {
    @Override
    public Response getResponse(HttpStatus code, String status, String description) {
        return new Response(code, status, description);
    }
}
