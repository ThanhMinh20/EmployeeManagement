<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <li>Lương: ${employee.salary}</li>
        <li>Phòng ban: ${employee.departmentId}</li>
        <li>Trạng thái: ${employee.status}</li>
        <li>Ngày vào: ${employee.hireDate}</li>
    </ul>
    <form action="employees" method="post">
        <input type="hidden" name="action" value="delete">
        <input type="hidden" name="id" value="${employee.id}">
        <button type="submit">Xóa</button>
        <a href="employees?action=list">Hủy</a>
    </form>
</body>
</html>