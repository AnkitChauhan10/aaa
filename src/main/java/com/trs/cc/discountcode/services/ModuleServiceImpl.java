package com.trs.cc.discountcode.services;

import com.trs.cc.discountcode.constant.ExceptionConstant;
import com.trs.cc.discountcode.decorator.ModuleRequest;
import com.trs.cc.discountcode.exception.AlreadyExistException;
import com.trs.cc.discountcode.model.Module;
import com.trs.cc.discountcode.repository.ModuleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public String addModule(ModuleRequest moduleRequest) throws AlreadyExistException {
        if (moduleRepository.existsByNameAndSoftDeleteIsFalse(moduleRequest.getName())) throw new AlreadyExistException(ExceptionConstant.MODULE_ALREADY_EXISTS);
        Module module = modelMapper.map(moduleRequest, Module.class);
        module = moduleRepository.save(module);
        return module.getId();
    }
}
