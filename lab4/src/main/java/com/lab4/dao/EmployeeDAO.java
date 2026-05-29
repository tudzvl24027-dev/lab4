package com.lab4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lab4.model.Employee;

public class EmployeeDAO {
    private static final Logger LOGGER = Logger.getLogger(EmployeeDAO.class.getName());
    private final String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=lab4_db;encrypt=true;trustServerCertificate=true";
    private final String jdbcUsername = "sa";
    private final String jdbcPassword = "161207";

    private static final String INSERT = "INSERT INTO employees (emp_code, full_name, email) VALUES (?, ?, ?)";
    private static final String SELECT_BY_CODE = "SELECT * FROM employees WHERE emp_code = ?";
    private static final String SELECT_ALL = "SELECT * FROM employees";
    private static final String UPDATE = "UPDATE employees SET full_name=?, email=? WHERE emp_code=?";
    private static final String DELETE = "DELETE FROM employees WHERE emp_code=?";

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found", e);
        }
    }

    public void insertEmployee(Employee e) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {
            ps.setString(1, e.getEmpCode());
            ps.setString(2, e.getFullName());
            ps.setString(3, e.getEmail());
            ps.executeUpdate();
        }
    }

    public Employee selectEmployee(String code) {
        String sql = SELECT_BY_CODE;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee e = new Employee();
                e.setEmpCode(rs.getString("emp_code"));
                e.setFullName(rs.getString("full_name"));
                e.setEmail(rs.getString("email"));
                return e;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error selecting employee by code", e);
        }
        return null;
    }

    public List<Employee> selectAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = SELECT_ALL;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmpCode(rs.getString("emp_code"));
                e.setFullName(rs.getString("full_name"));
                e.setEmail(rs.getString("email"));
                list.add(e);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error selecting all employees", e);
        }
        return list;
    }

    public boolean updateEmployee(Employee e) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1, e.getFullName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getEmpCode());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteEmployee(String code) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setString(1, code);
            return ps.executeUpdate() > 0;
        }
    }
}