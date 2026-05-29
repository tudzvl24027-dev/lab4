package com.lab4.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab4.dao.EmployeeDAO;
import com.lab4.model.Employee;

@WebServlet("/employees/view")
public class EmployeeViewServlet extends HttpServlet {
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
        req.getRequestDispatcher("/employee/view.jsp").forward(req, resp);
    }
}