<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Xóa nhân viên</title>
</head>
<body>
    <h2>Xác nhận xóa nhân viên</h2>
    <p>Bạn có chắc chắn muốn xóa nhân viên sau?</p>
    <ul>
        <li>ID: ${employee.id}</li>
        <li>Tên: ${employee.name}</li>
        <li>Lương: <fmt:formatNumber value="${employee.salary}" type="currency"/></li>
        <li>Phòng ban: ${employee.departmentId}</li>
        <li>Trạng thái: ${employee.status}</li>
        <li>Ngày vào: <fmt:formatDate value="${employee.hireDate}" pattern="dd/MM/yyyy"/></li>
    </ul>
    <form action="employee" method="post">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="id" value="${employee.id}">
        <button type="submit">Xóa</button>
        <a href="employee?action=list">Hủy</a>
    </form>
</body>
</html>