<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Kết quả</title></head>
<body>
<h2>Thông tin đã đăng ký</h2>
<ul>
    <li>Họ tên: ${user.fullname}</li>
    <li>Email: ${user.email}</li>
    <li>Giới tính: ${user.gender}</li>
    <li>Chuyên ngành: ${user.major}</li>
</ul>
<a href="${pageContext.request.contextPath}/register.jsp">Quay lại đăng ký</a>
</body>
</html>