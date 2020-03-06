package com.trs.cc.discountcode.controller;

import com.trs.cc.discountcode.constant.ResponseConstant;
import com.trs.cc.discountcode.decorator.DataResponse;
import com.trs.cc.discountcode.decorator.ModuleRequest;
import com.trs.cc.discountcode.decorator.Response;
import com.trs.cc.discountcode.exception.AlreadyExistException;
import com.trs.cc.discountcode.services.ModuleService;
import com.trs.cc.discountcode.utils.Access;
import com.trs.cc.discountcode.utils.Roles;
import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("module-controller")
@Api
public class ModuleController {
    @Autowired
    ModuleService moduleService;

    @RequestMapping(name = "addModule", value = "/addModule", method = RequestMethod.POST)
    @Access(levels = {Roles.ADMIN})
    public DataResponse<String> addModule(
            @RequestBody ModuleRequest moduleRequest
    ) {
        DataResponse<String> dataResponse = new DataResponse<>();
        try {
            dataResponse.setData(moduleService.addModule(moduleRequest));
            dataResponse.setStatus(ResponseConstant.OK_RESPONSE);
        } catch (AlreadyExistException e) {
            dataResponse.setStatus(new Response(HttpStatus.CONFLICT, ResponseConstant.FAIL, e.getLocalizedMessage()));
        }
        return dataResponse;
    }
}
