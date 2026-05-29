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

import com.lab4.dao.UserDAO;
import com.lab4.model.User;
import com.lab4.util.ValidationUtils;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class.getName());
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errors = new HashMap<>();

        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String major = req.getParameter("major");

        if (!ValidationUtils.isNotEmpty(fullname)) {
            errors.put("fullname", "Họ tên không được để trống");
        }
        if (!ValidationUtils.isValidEmail(email)) {
            errors.put("email", "Email phải chứa ký tự @");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("fullname", fullname);
            req.setAttribute("email", email);
            req.setAttribute("gender", gender);
            req.setAttribute("major", major);
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        User user = new User(fullname, email, gender, major);
        try {
            userDAO.insertUser(user);
            req.setAttribute("user", user);
            req.getRequestDispatcher("result.jsp").forward(req, resp);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving user: " + email, e);
            errors.put("db", "Lỗi lưu dữ liệu: " + e.getMessage());
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}