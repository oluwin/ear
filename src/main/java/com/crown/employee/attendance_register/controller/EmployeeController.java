package com.crown.employee.attendance_register.controller;

import com.crown.employee.attendance_register.data.ResponseDto;
import com.crown.employee.attendance_register.data.EmployeeDto;
import com.crown.employee.attendance_register.data.EmployeeResponseDto;
import com.crown.employee.attendance_register.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Add a new employee
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<EmployeeResponseDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeResponseDto employeeResponseDto = employeeService.addEmployee(employeeDto);
        return ResponseDto.success("Employee added successfully", employeeResponseDto);
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseDto<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDto employeeResponseDto = employeeService.getEmployeeById(id);
        return ResponseDto.success("Employee retrieved successfully", employeeResponseDto);
    }

    // Get all employees
    @GetMapping
    public ResponseDto<List<EmployeeResponseDto>> getAllEmployees() {
        List<EmployeeResponseDto> employees = employeeService.getAllEmployees();
        return ResponseDto.success("Employees retrieved successfully", employees);
    }

    // Update an existing employee
    @PutMapping("/{id}")
    public ResponseDto<EmployeeResponseDto> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto
    ) {
        EmployeeResponseDto employeeResponseDto = employeeService.updateEmployee(id, employeeDto);
        return ResponseDto.success("Employee updated successfully", employeeResponseDto);
    }

    // Filter employees by department
    @GetMapping("/{departmentId}/department")
    public ResponseDto<List<EmployeeResponseDto>> getEmployeesByDepartmentId(@PathVariable Long departmentId) {
        List<EmployeeResponseDto> employees = employeeService.getEmployeesByDepartmentId(departmentId);
        return ResponseDto.success("Employees filtered by department", employees);
    }
}