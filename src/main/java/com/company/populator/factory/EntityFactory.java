package com.company.populator.factory;

import com.company.entity.Entity;
import com.company.entity.bean.formbean.FormBean;
import com.company.populator.entity.EntityPopulator;

import java.util.Map;

public class EntityFactory {

    private Map<Class, EntityPopulator> factoryMap;

    public EntityFactory(Map<Class, EntityPopulator> factoryMap){
        this.factoryMap = factoryMap;
    }

    public Entity create(Class clazz, FormBean form){
        return factoryMap.get(clazz).populate(form);
    }
}
