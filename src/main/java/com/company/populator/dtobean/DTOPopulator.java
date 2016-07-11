package com.company.populator.dtobean;

import com.company.entity.Entity;
import com.company.entity.bean.dtobean.DTOBean;

public interface DTOPopulator {

    DTOBean populate(Entity entity);
}
