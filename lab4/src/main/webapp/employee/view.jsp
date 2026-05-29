<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Chi tiết nhân viên</title></head>
<body>
<h2>Chi tiết nhân viên</h2>
<p>Mã NV: ${employee.empCode}</p>
<p>Họ tên: ${employee.fullName}</p>
<p>Email: ${employee.email}</p>
<a href="${pageContext.request.contextPath}/employees">Quay lại danh sách</a>
</body>
</html>