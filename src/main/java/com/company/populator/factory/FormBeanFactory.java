package com.company.populator.factory;

import com.company.entity.bean.formbean.FormBeans;
import com.company.entity.bean.Bean;
import com.company.populator.formbean.FormBeanPopulator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class FormBeanFactory {

    private Map<FormBeans, FormBeanPopulator> factoryMap;

    public FormBeanFactory(Map<FormBeans, FormBeanPopulator> factoryMap){
        this.factoryMap = factoryMap;
    }

    public Bean create(FormBeans form, HttpServletRequest req){
        return factoryMap.get(form).populate(req);
    }
}
