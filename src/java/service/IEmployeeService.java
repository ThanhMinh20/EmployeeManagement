/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Employee;

public interface IEmployeeService {

    List<Employee> getAllEmployees();

    void createEmployee(Employee e) throws Exception;

    void updateEmployee(Employee e) throws Exception;

    void deleteEmployee(int id);

    void changeStatus(int id, String status);

    // ===== Module 6 - Session Flow =====
    void addTempEmployee(HttpSession session, Employee e) throws Exception;

    List<Employee> getTempList(HttpSession session);

    void removeTempEmployee(HttpSession session, int id);

    void confirmInsert(HttpSession session) throws Exception;
}
