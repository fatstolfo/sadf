package com.company.dao;

import com.company.model.Role;

public interface RoleDao {
    Role get(int id);
    void create(Role role);

}
