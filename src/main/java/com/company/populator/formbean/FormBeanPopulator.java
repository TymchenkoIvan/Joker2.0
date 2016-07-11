package com.company.populator.formbean;

import com.company.entity.bean.formbean.FormBean;

import javax.servlet.http.HttpServletRequest;

public interface FormBeanPopulator {

    FormBean populate(HttpServletRequest req);
}
