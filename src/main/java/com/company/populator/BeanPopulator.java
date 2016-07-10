package com.company.populator;

import com.company.entity.bean.Bean;

import javax.servlet.http.HttpServletRequest;

public interface BeanPopulator {

    Bean populate(HttpServletRequest req);
}
