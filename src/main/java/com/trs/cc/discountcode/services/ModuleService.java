package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.decorator.ModuleRequest;
import com.trs.cc.discountcode.exception.AlreadyExistException;

public interface ModuleService {

    String addModule(ModuleRequest moduleRequest) throws AlreadyExistException;
}
