package com.company.populator;

import com.company.entity.bean.formbean.FormBeans;
import com.company.entity.bean.Bean;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BeanFactory {

    private Map<FormBeans, BeanPopulator> factoryMap;

    public BeanFactory(Map<FormBeans, BeanPopulator> factoryMap){
        this.factoryMap = factoryMap;
    }

    public Bean create(FormBeans form, HttpServletRequest req){
        return factoryMap.get(form).populate(req);
    }
}
