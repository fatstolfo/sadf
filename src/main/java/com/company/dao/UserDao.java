package com.company.dao;

import com.company.model.User;

public interface UserDao {
    User get(int id);
    void create(User item);
}
