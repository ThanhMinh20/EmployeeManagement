<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Danh sách nhân viên</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .search-box { margin-bottom: 15px; }
        .message { color: green; }
        .error { color: red; }
    </style>
</head>
<body>
    <h2>Danh sách nhân viên</h2>

    <!-- Hiển thị thông báo -->
    <c:if test="${not empty message}">
        <p class="message">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <!-- Ô tìm kiếm -->
    <div class="search-box">
        <form action="employees" method="get">
            <input type="hidden" name="action" value="search">
            <input type="text" name="keyword" placeholder="Tìm theo tên...">
            <button type="submit">Tìm</button>
        </form>
    </div>

    <!-- Nút thêm mới (chỉ ADMIN) -->
    <c:if test="${sessionScope.LOGIN_USER.role == 'ADMIN'}">
        <a href="employees?action=createForm">Thêm nhân viên</a>
    </c:if>

    <table>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Lương</th>
            <th>Phòng ban</th>
            <th>Trạng thái</th>
            <th>Ngày vào</th>
            <c:if test="${sessionScope.LOGIN_USER.role == 'ADMIN'}">
                <th>Hành động</th>
            </c:if>
        </tr>
        <c:forEach var="emp" items="${employees}">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.name}</td>
                <td><fmt:formatNumber value="${emp.salary}" type="currency"/></td>
                <td>${emp.departmentId}</td> <!-- Có thể hiển thị tên phòng nếu có thêm logic -->
                <td>${emp.status}</td>
                <td><fmt:formatDate value="${emp.hireDate}" pattern="dd/MM/yyyy"/></td>
                <c:if test="${sessionScope.LOGIN_USER.role == 'ADMIN'}">
                    <td>
                        <a href="employees?action=editForm&id=${emp.id}">Sửa</a> |
                        <a href="employees?action=deleteForm&id=${emp.id}">Xóa</a> |
                        <a href="employees?action=changeStatus&id=${emp.id}&status=PENDING">Pending</a> |
                        <a href="employees?action=changeStatus&id=${emp.id}&status=WORKING">Working</a> |
                        <a href="employees?action=changeStatus&id=${emp.id}&status=ACTIVE">Active</a> |
                        <a href="employees?action=changeStatus&id=${emp.id}&status=TERMINATED">Terminated</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
