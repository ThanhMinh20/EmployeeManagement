<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Sửa nhân viên</title>
</head>
<body>
    <h2>Sửa thông tin nhân viên</h2>
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
    <form action="employees" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${employee.id}">
        <label>Tên:</label> <input type="text" name="name" value="${employee.name}" required/><br/>
        <label>Lương:</label> <input type="number" step="0.01" name="salary" value="${employee.salary}" required/><br/>
        <label>Mã phòng ban:</label> <input type="number" name="departmentId" value="${employee.departmentId}" required/><br/>
        <label>Trạng thái:</label>
        <select name="status">
            <option value="PENDING" ${employee.status == 'PENDING' ? 'selected' : ''}>PENDING</option>
            <option value="ACTIVE" ${employee.status == 'ACTIVE' ? 'selected' : ''}>ACTIVE</option>
            <option value="TERMINATED" ${employee.status == 'TERMINATED' ? 'selected' : ''}>TERMINATED</option>
        </select><br/>
        <label>Ngày vào:</label>
        <input type="date" name="hireDate" value="<fmt:formatDate value='${employee.hireDate}' pattern='yyyy-MM-dd'/>" required/><br/>
        <button type="submit">Cập nhật</button>
        <a href="employees?action=list">Hủy</a>
    </form>
</body>
</html>