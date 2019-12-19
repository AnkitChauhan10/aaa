package com.trs.cc.sponsor.config;


import com.trs.cc.sponsor.decorator.RequestSession;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class GeneralBeans {

    @Bean
    ModelMapper getModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    @RequestScope
    public RequestSession getRequestSession(){
        return new RequestSession();
    }

}
