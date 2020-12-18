package com.company.dao;

import com.company.model.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDaoJDBCImpl implements RoleDao {

    private Connection connection;

    public RoleDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMapper<Role> roleRowMapper = row -> new Role(
            row.getInt("id"),
            row.getString("role")
    );

    @Override
    public Role get(int id) {
        String SQL = "SELECT * from public.role WHERE id = ?";
        try (PreparedStatement stat = connection.prepareStatement(SQL)) {
            stat.setInt(1, id); //!
            try (ResultSet rs = stat.executeQuery()) {
                if (rs.next()) {
                    return roleRowMapper.mapRow(rs); //!
                }
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Role item) {
        int i = 1;
        String SQL = "INSERT INTO public.role(id, role) " +
                "VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(SQL)) {
            stmt.setInt(i++, item.getId());
            stmt.setString(i++, item.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
