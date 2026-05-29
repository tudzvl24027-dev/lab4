package com.lab4.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab4.dao.EmployeeDAO;
import com.lab4.model.Employee;

@WebServlet("/employees")
public class EmployeeListServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = employeeDAO.selectAllEmployees();
        req.setAttribute("employees", employees);
        req.getRequestDispatcher("/employee/list.jsp").forward(req, resp);
    }
}