<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Thêm nhân viên</title>
</head>
<body>
    <h2>Thêm nhân viên mới</h2>
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
    <form action="employees" method="post">
        <input type="hidden" name="action" value="create">
        <label>Tên:</label> <input type="text" name="name" required/><br/>
        <label>Lương:</label> <input type="number" step="0.01" name="salary" required/><br/>
        <label>Mã phòng ban:</label> <input type="number" name="departmentId" required/><br/>
        <label>Trạng thái:</label>
        <select name="status">
            <option value="PENDING">PENDING</option>
            <option value="ACTIVE">ACTIVE</option>
            <option value="TERMINATED">TERMINATED</option>
        </select><br/>
        <label>Ngày vào:</label> <input type="date" name="hireDate" required/><br/>
        <button type="submit">Lưu</button>
        <a href="employees?action=list">Hủy</a>
    </form>
</body>
</html>