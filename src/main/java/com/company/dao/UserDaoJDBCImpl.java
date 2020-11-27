package com.company.dao;

import com.company.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<User> userRowMapper = row -> new User(row.getInt("id"),
            row.getString("username"),
            row.getString("password"),
            row.getString("email"));


    @Override
    public User get(int id) {
        String SQL = "SELECT * from public.user WHERE id = ?";
        try (PreparedStatement stat = connection.prepareStatement(SQL)) {
            stat.setInt(1, id);
//            stat.setString(2, email);
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    return userRowMapper.mapRow(rs);
                }
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(User item) {

    }
}
