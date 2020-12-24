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

    private RowMapper<User> userRowMapper = row -> new User(
            row.getInt("id"),
            row.getString("username"),
            row.getString("password"),
            row.getString("email"));


    @Override
    public User get(int id) {
        String SQL = "SELECT * from public.users WHERE id = ?";
        try (PreparedStatement stat = connection.prepareStatement(SQL)) {
            stat.setInt(1, id); //!
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    return userRowMapper.mapRow(rs); //!
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
        int i = 1;
        String SQL = "INSERT INTO public.users(username, password, email) " +
                "VALUES (?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setString(i++, item.getUsername());
            stmt.setString(i++, item.getPassword());
            stmt.setString(i++, item.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User get(String email) {
        String SQL = "SELECT * from public.users WHERE email = ?";
        try (PreparedStatement stat = connection.prepareStatement(SQL)) {
            stat.setString(1, email); //!
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    return userRowMapper.mapRow(rs); //!
                }
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
