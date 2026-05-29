package com.lab4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab4.dao.EmployeeDAO;

@WebServlet("/employees/delete")
public class EmployeeDeleteServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(EmployeeDeleteServlet.class.getName());
    private EmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empCode = req.getParameter("emp_code");
        try {
            employeeDAO.deleteEmployee(empCode);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting employee: " + empCode, e);
        }
        resp.sendRedirect(req.getContextPath() + "/employees");
    }
}