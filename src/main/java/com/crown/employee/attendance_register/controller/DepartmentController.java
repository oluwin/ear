package com.crown.employee.attendance_register.controller;

import com.crown.employee.attendance_register.data.ResponseDto;
import com.crown.employee.attendance_register.data.DepartmentDto;
import com.crown.employee.attendance_register.data.DepartmentResponseDto;
import com.crown.employee.attendance_register.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Add a new department
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<DepartmentResponseDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentResponseDto departmentResponseDto = departmentService.addDepartment(departmentDto);
        return ResponseDto.success("Department added successfully", departmentResponseDto);
    }

    // Get department by ID
    @GetMapping("/{id}")
    public ResponseDto<DepartmentResponseDto> getDepartmentById(@PathVariable Long id) {
        DepartmentResponseDto departmentResponseDto = departmentService.getDepartmentById(id);
        return ResponseDto.success("Department retrieved successfully", departmentResponseDto);
    }

    // Get all departments
    @GetMapping
    public ResponseDto<List<DepartmentResponseDto>> getAllDepartments() {
        List<DepartmentResponseDto> departments = departmentService.getAllDepartments();
        return ResponseDto.success("Departments retrieved successfully", departments);
    }

    // Update an existing department
    @PutMapping("/{id}")
    public ResponseDto<DepartmentResponseDto> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentDto departmentDto
    ) {
        DepartmentResponseDto departmentResponseDto = departmentService.updateDepartment(id, departmentDto);
        return ResponseDto.success("Department updated successfully", departmentResponseDto);
    }

    // Delete a department
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseDto<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseDto.success("Department deleted successfully", null);
    }
}