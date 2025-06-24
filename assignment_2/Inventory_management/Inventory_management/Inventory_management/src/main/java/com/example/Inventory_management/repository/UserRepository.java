package com.example.Inventory_management.repository;



import com.example.Inventory_management.entity.Role;
import com.example.Inventory_management.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new Object[]{username}, this::mapUser);
        return users.isEmpty() ? null : users.get(0);
    }

    private User mapUser(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));

        // Load roles
        String roleSql = "SELECT r.id, r.name FROM roles r JOIN user_roles ur ON r.id = ur.role_id WHERE ur.user_id = ?";
        List<Role> roles = jdbcTemplate.query(roleSql, new Object[]{user.getId()}, (rsr, rowNumr) -> {
            Role role = new Role();
            role.setId(rsr.getInt("id"));
            role.setName(rsr.getString("name"));
            return role;
        });
        user.setRoles(roles);

        return user;
    }

    // Add registration, update methods etc.
}

