package com.company.DAO;

import com.company.entity.Role;

public interface RoleDAO {

    Role getRole(int id);

    Role getRole(String role);
}
