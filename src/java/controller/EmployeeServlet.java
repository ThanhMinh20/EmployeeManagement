/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.Employee;
import service.EmployeeService;
import service.IEmployeeService;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employee"})
public class EmployeeServlet extends HttpServlet {

    private IEmployeeService service = new EmployeeService();

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {

            switch (action) {

                // ================= LIST =================
                case "list":
                    List<Employee> list = service.getAllEmployees();
                    request.setAttribute("EMP_LIST", list);
                    request.getRequestDispatcher("employees/list.jsp")
                            .forward(request, response);
                    break;

                // ================= CREATE =================
                case "create":
                    createEmployee(request, response);
                    break;

                // ================= UPDATE =================
                case "update":
                    updateEmployee(request, response);
                    break;

                // ================= DELETE =================
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    service.deleteEmployee(id);
                    response.sendRedirect("employee?action=list");
                    break;

                // ================= CHANGE STATUS =================
                case "changeStatus":
                    int empId = Integer.parseInt(request.getParameter("id"));
                    String status = request.getParameter("status");
                    service.changeStatus(empId, status);
                    response.sendRedirect("employee?action=list");
                    break;

                // ================= MODULE 6 =================
                case "addTemp":
                    addTempEmployee(request, response);
                    break;

                case "confirmInsert":
                    confirmInsert(request, response);
                    break;

                default:
                    response.sendRedirect("employee?action=list");
            }

        } catch (Exception e) {
            request.setAttribute("ERROR", e.getMessage());
            request.getRequestDispatcher("error.jsp")
                    .forward(request, response);
        }
    }

    // ================= CREATE =================
    private void createEmployee(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Employee e = extractEmployeeFromRequest(request);

        service.createEmployee(e);

        response.sendRedirect("employee?action=list");
    }

    // ================= UPDATE =================
    private void updateEmployee(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Employee e = extractEmployeeFromRequest(request);

        service.updateEmployee(e);

        response.sendRedirect("employee?action=list");
    }

    // ================= MODULE 6 =================
    private void addTempEmployee(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Employee e = extractEmployeeFromRequest(request);

        HttpSession session = request.getSession();

        service.addTempEmployee(session, e);

        response.sendRedirect("employees/tempList.jsp");
    }

    private void confirmInsert(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        service.confirmInsert(session);

        response.sendRedirect("employee?action=list");
    }

    // ================= COMMON METHOD =================
    private Employee extractEmployeeFromRequest(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String status = request.getParameter("status");

        Employee e = new Employee();
        e.setId(id);
        e.setName(name);
        e.setSalary(salary);
        e.setDepartmentId(departmentId);
        e.setStatus(status);

        return e;
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
