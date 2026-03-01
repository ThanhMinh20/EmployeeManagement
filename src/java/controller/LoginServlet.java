/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dao.DBConnection;
import dao.UserDAO;
import model.User;
import jakarta.servlet.http.*;
/**
 *
 * @author minht
 */
@WebServlet("/checkLogin")
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
    String password = request.getParameter("password");

    // 2. Gọi DAO xử lý (Đây là cách chuyên nghiệp)
    UserDAO dao = new UserDAO(); 
    User user = dao.checkLogin(username, password);

    // 3. Xử lý kết quả
    if (user != null) {
        // Login thành công
        HttpSession session = request.getSession(true);
        session.setAttribute("LOGIN_USER", user);
        
        // Chuyển hướng theo Role (Admin/User)
        // Chú ý: Database của bạn để role là 'admin' (viết thường)
        if ("admin".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/employee?action=list");
        } else {
            response.sendRedirect(request.getContextPath() + "/employee?action=list");
        }
    } else {
        // Login thất bại - Dùng đúng tên attribute "error" mà JSP đang đợi
        request.setAttribute("error", "Invalid username or password");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
}