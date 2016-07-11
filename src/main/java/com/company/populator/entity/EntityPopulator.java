package com.company.populator.entity;

import com.company.entity.Entity;
import com.company.entity.bean.formbean.FormBean;

public interface EntityPopulator {
    Entity populate(FormBean formBean);
}
