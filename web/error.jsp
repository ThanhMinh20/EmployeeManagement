<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Lỗi</title>
</head>
<body>
    <h2>Đã xảy ra lỗi</h2>
    <p style="color:red">
        <c:out value="${error}" default="Lỗi không xác định."/>
    </p>
    <a href="employees?action=list">Quay lại danh sách</a>
</body>
</html>