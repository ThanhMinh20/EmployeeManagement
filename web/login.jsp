<%-- 
    Document   : login
    Created on : Feb 26, 2026, 9:58:39 PM
    Author     : minht
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Employee Management</title>
</head>
<body>

    <h2>Employee Management System</h2>
    <h3>Login</h3>

    <!-- HIEN THI LOI -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p style="color:red;"><%= error %></p>
    <%
        }
    %>

    <!-- FORM LOGIN -->
    <form action="checkLogin" method="post">

        <table>
            <tr>
                <td>Username:</td>
                <td>
                    <input type="text" name="username" required />
                </td>
            </tr>

            <tr>
                <td>Password:</td>
                <td>
                    <input type="password" name="password" required />
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="Login"/>
                </td>
            </tr>
        </table>

    </form>

</body>
</html>