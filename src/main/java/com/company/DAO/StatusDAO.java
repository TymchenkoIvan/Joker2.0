package com.company.DAO;

import com.company.entity.Status;

public interface StatusDAO {

    Status getStatus(int id);

    Status getStatus(String status);
}
