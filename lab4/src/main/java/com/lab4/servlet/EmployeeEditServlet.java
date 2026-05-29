package com.lab4.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab4.dao.EmployeeDAO;
import com.lab4.model.Employee;
import com.lab4.util.ValidationUtils;

@WebServlet("/employees/edit")
public class EmployeeEditServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(EmployeeEditServlet.class.getName());
    private EmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empCode = req.getParameter("code");
        Employee emp = employeeDAO.selectEmployee(empCode);
        req.setAttribute("employee", emp);
        req.getRequestDispatcher("/employee/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();

        String empCode = req.getParameter("emp_code");
        String fullName = req.getParameter("full_name");
        String email = req.getParameter("email");

        if (!ValidationUtils.isNotEmpty(fullName)) {
            errors.put("full_name", "Họ tên không được trống");
        }
        if (!ValidationUtils.isValidEmail(email)) {
            errors.put("email", "Email phải chứa ký tự @");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            Employee emp = new Employee(empCode, fullName, email);
            req.setAttribute("employee", emp);
            req.getRequestDispatcher("/employee/edit.jsp").forward(req, resp);
            return;
        }

        Employee emp = new Employee(empCode, fullName, email);
        try {
            employeeDAO.updateEmployee(emp);
            resp.sendRedirect(req.getContextPath() + "/employees");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating employee: " + empCode, e);
            errors.put("db", "Lỗi cập nhật: " + e.getMessage());
            req.setAttribute("errors", errors);
            req.setAttribute("employee", emp);
            req.getRequestDispatcher("/employee/edit.jsp").forward(req, resp);
        }
    }
}