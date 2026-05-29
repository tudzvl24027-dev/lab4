<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Thêm nhân viên</title></head>
<body>
<h2>Thêm nhân viên</h2>
<form action="${pageContext.request.contextPath}/employees/create" method="post">
    Mã NV: <input type="text" name="emp_code" required pattern="NV[0-9]{3}" title="VD: NV001">
    <c:if test="${not empty errors.emp_code}"><span style="color:red;">${errors.emp_code}</span></c:if><br>
    Họ tên: <input type="text" name="full_name" required>
    <c:if test="${not empty errors.full_name}"><span style="color:red;">${errors.full_name}</span></c:if><br>
    Email: <input type="email" name="email" required>
    <c:if test="${not empty errors.email}"><span style="color:red;">${errors.email}</span></c:if><br>
    <input type="submit" value="Tạo">
</form>
<a href="${pageContext.request.contextPath}/employees">Quay lại</a>
</body>
</html>