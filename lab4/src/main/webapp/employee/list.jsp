<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Danh sách nhân viên</title></head>
<body>
<h2>Danh sách nhân viên</h2>
<table border="1">
    <tr><th>Mã NV</th><th>Họ tên</th><th>Email</th><th>Hành động</th></tr>
    <c:forEach var="e" items="${employees}">
        <tr>
            <td>${e.empCode}</td>
            <td>${e.fullName}</td>
            <td>${e.email}</td>
            <td>
                <a href="${pageContext.request.contextPath}/employees/view?code=${e.empCode}">Xem</a>
                <a href="${pageContext.request.contextPath}/employees/edit?code=${e.empCode}">Sửa</a>
                <form action="${pageContext.request.contextPath}/employees/delete" method="post" style="display:inline;" onsubmit="return confirm('Bạn có chắc muốn xóa?');">
                    <input type="hidden" name="emp_code" value="${e.empCode}">
                    <button type="submit">Xóa</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/employees/create">Thêm nhân viên</a>
</body>
</html>