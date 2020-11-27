package com.company.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<G> {
 G mapRow(ResultSet row) throws SQLException;
}
