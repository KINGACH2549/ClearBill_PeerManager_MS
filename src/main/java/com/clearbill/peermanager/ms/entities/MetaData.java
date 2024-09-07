package com.clearbill.peermanager.ms.entities;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class MetaData {

    private Integer allowedIndividuals;

    public Integer getAllowedIndividuals() {
        return allowedIndividuals;
    }

    public void setAllowedIndividuals(Integer allowedIndividuals) {
        this.allowedIndividuals = allowedIndividuals;
    }
}
