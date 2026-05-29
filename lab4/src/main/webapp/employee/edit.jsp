<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Sửa nhân viên</title></head>
<body>
<h2>Sửa nhân viên</h2>
<form action="${pageContext.request.contextPath}/employees/edit" method="post">
    <input type="hidden" name="emp_code" value="${employee.empCode}">
    Mã NV: ${employee.empCode}<br>
    Họ tên: <input type="text" name="full_name" value="${employee.fullName}" required>
    <c:if test="${not empty errors.full_name}"><span style="color:red;">${errors.full_name}</span></c:if><br>
    Email: <input type="email" name="email" value="${employee.email}" required>
    <c:if test="${not empty errors.email}"><span style="color:red;">${errors.email}</span></c:if><br>
    <input type="submit" value="Cập nhật">
</form>
<a href="${pageContext.request.contextPath}/employees">Quay lại</a>
</body>
</html>