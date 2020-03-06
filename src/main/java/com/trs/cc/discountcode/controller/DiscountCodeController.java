package com.trs.cc.discountcode.controller;

import com.trs.cc.discountcode.decorator.DataResponse;
import com.trs.cc.discountcode.decorator.DiscountCodeRequest;
import com.trs.cc.discountcode.decorator.DiscountCodeResponse;
import com.trs.cc.discountcode.utils.Access;
import com.trs.cc.discountcode.utils.Roles;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("discount-code-controller")
@Api
public class DiscountCodeController {

    @RequestMapping(name="addDiscountCode",value = "/addDiscountCode",method = RequestMethod.POST)
    @Access(levels = {Roles.ANONYMOUS})
    public DataResponse<DiscountCodeResponse> getEventSessionWithTiming(
            @RequestBody DiscountCodeRequest discountCodeRequest
            ) {
        DataResponse<DiscountCodeResponse> dataResponse =  new DataResponse<>();

        return dataResponse;
    }

}
