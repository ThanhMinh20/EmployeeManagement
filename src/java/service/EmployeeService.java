/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.EmployeeDAO;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

public class EmployeeService implements IEmployeeService {

    private EmployeeDAO dao = new EmployeeDAO();

    // ================= CRUD =================

    @Override
    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();
    }
    
    public List<Employee> searchByName(String keyword)
    {
        return dao.searchByName(keyword);
    }

    @Override
    public void createEmployee(Employee e) throws Exception {
        validateEmployee(e);
        dao.insertEmp(e);
    }

    @Override
    public void updateEmployee(Employee e) throws Exception {
        validateEmployee(e);
        dao.updateEmp(e);
    }
    
    @Override
    public Employee getEmpById(int id) throws Exception
    {
        return dao.getEmpById(id);
    }

    @Override
    public void deleteEmployee(int id) {
        dao.deleteEmp(id);
    }

    @Override
    public void changeStatus(int id, String status) {
        dao.updateStatus(id, status);
    }

    // ================= Validation =================

    private void validateEmployee(Employee e) throws Exception {

        if (e.getName() == null || e.getName().trim().isEmpty()) {
            throw new Exception("Name cannot be empty");
        }

        if (e.getSalary() < 0) {
            throw new Exception("Salary must be >= 0");
        }

        if (e.getDepartmentId() <= 0) {
            throw new Exception("Department invalid");
        }
    }

    // ================= MODULE 6 - SESSION FLOW =================

    @Override
    public void addTempEmployee(HttpSession session, Employee e) throws Exception {

        validateEmployee(e);

        List<Employee> tempList =
                (List<Employee>) session.getAttribute("TEMP_EMP_LIST");

        if (tempList == null) {
            tempList = new ArrayList<>();
        }

        tempList.add(e);

        session.setAttribute("TEMP_EMP_LIST", tempList);
    }

    @Override
    public List<Employee> getTempList(HttpSession session) {
        List<Employee> tempList =
                (List<Employee>) session.getAttribute("TEMP_EMP_LIST");

        if (tempList == null) {
            tempList = new ArrayList<>();
        }

        return tempList;
    }

    @Override
    public void removeTempEmployee(HttpSession session, int id) {

        List<Employee> tempList = getTempList(session);

        tempList.removeIf(emp -> emp.getId() == id);

        session.setAttribute("TEMP_EMP_LIST", tempList);
    }

    @Override
    public void confirmInsert(HttpSession session) throws Exception {

        List<Employee> tempList = getTempList(session);

        for (Employee e : tempList) {
            dao.insertEmp(e);
        }

        session.removeAttribute("TEMP_EMP_LIST");
    }
}
