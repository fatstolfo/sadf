package com.company.service;

import com.company.dao.UserDao;
import com.company.dao.UserDaoJDBCImpl;
import com.company.model.User;

public class UserService {
    UserDao udao = new UserDaoJDBCImpl(ConnectionManager.getConnection());

    public User get(int id) {
        return udao.get(id);
    }

    public void create(User item) {
        udao.create(item);
    }

    public boolean isRegistered(String email) {
        return udao.get(email) != null;
    }

}
