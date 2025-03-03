package com.crown.employee.attendance_register.controller;

import com.crown.employee.attendance_register.model.Employee;
import com.crown.employee.attendance_register.model.enums.EmployeeType;
import com.crown.employee.attendance_register.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/type/{employeeType}")
    public ResponseEntity<List<Employee>> getEmployeesByType(@PathVariable EmployeeType employeeType) {
        List<Employee> employees = employeeService.getEmployeesByType(employeeType);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable Long departmentId) {
        List<Employee> employees = employeeService.getEmployeesByDepartmentId(departmentId);
        return ResponseEntity.ok(employees);
    }
}