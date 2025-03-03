package com.crown.employee.attendance_register.service;

import com.crown.employee.attendance_register.model.Employee;
import com.crown.employee.attendance_register.model.enums.EmployeeType;
import com.crown.employee.attendance_register.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeesByType( EmployeeType employeeType) {
        return employeeRepository.findByEmployeeType(employeeType);
    }

    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
}