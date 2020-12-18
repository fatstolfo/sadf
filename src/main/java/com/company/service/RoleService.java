package com.company.service;

import com.company.dao.RoleDao;
import com.company.dao.RoleDaoJDBCImpl;
import com.company.model.Role;

public class RoleService {
    RoleDao rdaod = new RoleDaoJDBCImpl(ConnectionManager.getConnection());

    public Role get(int id) {
        return rdaod.get(id);
    }

    public void create(Role item) {
        rdaod.create(item);
    }

}
