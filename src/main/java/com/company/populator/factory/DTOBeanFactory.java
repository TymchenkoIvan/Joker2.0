package com.company.populator.factory;

import com.company.entity.Entity;
import com.company.entity.bean.dtobean.DTOBean;
import com.company.entity.bean.dtobean.DTOBeans;
import com.company.populator.dtobean.DTOPopulator;
import java.util.Map;

public class DTOBeanFactory {

    private Map<DTOBeans, DTOPopulator> factoryMap;

    public DTOBeanFactory(Map<DTOBeans, DTOPopulator> factoryMap){
        this.factoryMap = factoryMap;
    }

    public DTOBean create(DTOBeans dto, Entity entity){
        return factoryMap.get(dto).populate(entity);
    }
}
