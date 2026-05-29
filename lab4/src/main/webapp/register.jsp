<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Đăng ký</title></head>
<body>
<h2>Form Đăng Ký</h2>
<form action="${pageContext.request.contextPath}/register" method="post">
    <label>Họ tên:</label>
    <input type="text" name="fullname" value="${fullname}">
    <c:if test="${not empty errors.fullname}">
        <span style="color:red;">${errors.fullname}</span>
    </c:if>
    <br>

    <label>Email:</label>
    <input type="text" name="email" value="${email}">
    <c:if test="${not empty errors.email}">
        <span style="color:red;">${errors.email}</span>
    </c:if>
    <br>

    <label>Giới tính:</label>
    <input type="radio" name="gender" value="Nam" ${gender=='Nam'?'checked':''}>Nam
    <input type="radio" name="gender" value="Nữ" ${gender=='Nữ'?'checked':''}>Nữ
    <br>

    <label>Chuyên ngành:</label>
    <select name="major">
        <option value="CNTT" ${major=='CNTT'?'selected':''}>CNTT</option>
        <option value="Kinh tế" ${major=='Kinh tế'?'selected':''}>Kinh tế</option>
    </select>
    <br>

    <input type="submit" value="Đăng ký">
</form>
<c:if test="${not empty errors.db}">
    <p style="color:red;">${errors.db}</p>
</c:if>
</body>
</html>