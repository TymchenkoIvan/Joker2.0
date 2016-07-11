package com.company.populator.entity;

import com.company.entity.Entity;
import com.company.entity.bean.formbean.FormBean;

import javax.servlet.http.HttpServletRequest;

public interface EntityPopulator {
    Entity populate(FormBean formBean);
}
