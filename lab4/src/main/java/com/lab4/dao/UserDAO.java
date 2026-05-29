package com.lab4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lab4.model.User;

public class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private final String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=lab4_db;encrypt=true;trustServerCertificate=true";
    private final String jdbcUsername = "sa";
    private final String jdbcPassword = "161207";

    private static final String INSERT_USER = "INSERT INTO users (fullname, email, gender, major) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String UPDATE_USER = "UPDATE users SET fullname=?, email=?, gender=?, major=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found", e);
        }
    }

    public void insertUser(User user) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getMajor());
            ps.executeUpdate();
            
            // Lấy ID tự sinh (nếu cần)
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public User selectUser(int id) {
        String sql = SELECT_USER_BY_ID;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));
                user.setMajor(rs.getString("major"));
                return user;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error selecting user by id", e);
        }
        return null;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = SELECT_ALL_USERS;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));
                user.setMajor(rs.getString("major"));
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error selecting all users", e);
        }
        return users;
    }

    public boolean updateUser(User user) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_USER)) {
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getMajor());
            ps.setInt(5, user.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteUser(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_USER)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}