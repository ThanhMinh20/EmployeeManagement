package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
//import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import model.Employee;
import service.EmployeeService;
import service.IEmployeeService;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employee"})
public class EmployeeServlet extends HttpServlet {

    private IEmployeeService service = new EmployeeService();
    //private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

                // ================= SEARCH =================
                case "search":
                    String keyword = request.getParameter("keyword");
                    List<Employee> searchResult = service.searchByName(keyword);
                    request.setAttribute("EMP_LIST", searchResult);
                    request.getRequestDispatcher("employees/list.jsp")
                            .forward(request, response);
                    break;

                // ================= SHOW CREATE FORM =================
                case "create":
                    // Forward to create form
                    request.getRequestDispatcher("employees/create.jsp")
                            .forward(request, response);
                    break;

                // ================= CREATE (POST) =================
                case "insert":
                    Employee newEmp = extractEmployeeFromRequest(request, true); // true = ignore id
                    service.createEmployee(newEmp);
                    response.sendRedirect("employee?action=list");
                    break;

                // ================= SHOW EDIT FORM =================
                case "edit":
                    int editId = Integer.parseInt(request.getParameter("id"));
                    Employee emp = service.getEmpById(editId);
                    request.setAttribute("employee", emp);
                    request.getRequestDispatcher("employees/edit.jsp")
                            .forward(request, response);
                    break;

                // ================= UPDATE (POST) =================
                case "update":
                    Employee updateEmp = extractEmployeeFromRequest(request, false);
                    service.updateEmployee(updateEmp);
                    response.sendRedirect("employee?action=list");
                    break;

                // ================= SHOW DELETE CONFIRMATION =================
                case "confirmDelete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    Employee delEmp = service.getEmpById(deleteId);
                    request.setAttribute("employee", delEmp);
                    request.getRequestDispatcher("employees/delete.jsp")
                            .forward(request, response);
                    break;

                // ================= DELETE (POST) =================
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    service.deleteEmployee(id);
                    response.sendRedirect("employee?action=list");
                    break;

                // ================= CHANGE STATUS =================
                case "changeStatus":
                    int statusId = Integer.parseInt(request.getParameter("id"));
                    String status = request.getParameter("status");
                    service.changeStatus(statusId, status);
                    response.sendRedirect("employee?action=list");
                    break;

                // ================= MODULE 6 (temp) =================
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
            request.setAttribute("error", e.getMessage());
            request.setAttribute("errLocation", e.getStackTrace());
            request.getRequestDispatcher("error.jsp")
                    .forward(request, response);
        }
    }

    // ================= EXTRACT EMPLOYEE FROM REQUEST =================
    private Employee extractEmployeeFromRequest(HttpServletRequest request, boolean isCreate)
            throws Exception {
        Employee e = new Employee();

        // ID is only present for update/delete, not for create
        if (!isCreate) {
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                e.setId(Integer.parseInt(idParam));
            }
        }

        String name = request.getParameter("name");
        String salaryParam = request.getParameter("salary");
        String deptParam = request.getParameter("departmentId");
        String status = request.getParameter("status");
        String hireDateStr = request.getParameter("hireDate");

        e.setName(name);
        if (salaryParam != null && !salaryParam.isEmpty()) {
            e.setSalary(Double.parseDouble(salaryParam));
        }
        if (deptParam != null && !deptParam.isEmpty()) {
            e.setDepartmentId(Integer.parseInt(deptParam));
        }
        e.setStatus(status);

        if (hireDateStr != null && !hireDateStr.isEmpty()) {
            Date hireDate = Date.valueOf(hireDateStr);
            e.setHireDate(hireDate);
        }

        return e;
    }

    // ================= MODULE 6 METHODS =================
    private void addTempEmployee(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Employee e = extractEmployeeFromRequest(request, true);
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