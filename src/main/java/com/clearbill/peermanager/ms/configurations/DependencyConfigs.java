package com.clearbill.peermanager.ms.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyConfigs {


    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }


    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
