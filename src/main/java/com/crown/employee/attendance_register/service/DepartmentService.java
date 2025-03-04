package com.crown.employee.attendance_register.service;

import com.crown.employee.attendance_register.data.DepartmentDto;
import com.crown.employee.attendance_register.data.DepartmentResponseDto;
import com.crown.employee.attendance_register.exception.ResourceNotFoundException;
import com.crown.employee.attendance_register.model.Department;
import com.crown.employee.attendance_register.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Convert Department entity to DepartmentResponseDto
    private DepartmentResponseDto convertToDepartmentResponseDto(Department department) {
        return new DepartmentResponseDto(
                department.getId(),
                department.getName()
        );
    }

    // Convert DepartmentDto to Department entity
    private Department convertToDepartmentEntity(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());

        return department;
    }

    // Add a new department
    public DepartmentResponseDto addDepartment(DepartmentDto departmentDto) {
        Department department = convertToDepartmentEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return convertToDepartmentResponseDto(savedDepartment);
    }

    // Update action
    public DepartmentResponseDto updateDepartment(Long id, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        department.setName(departmentDto.getName());

        Department updatedDepartment = departmentRepository.save(department);
        return convertToDepartmentResponseDto(updatedDepartment);
    }

    // Fetch department by ID
    public DepartmentResponseDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        return convertToDepartmentResponseDto(department);
    }

    // Fetch all departments
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::convertToDepartmentResponseDto)
                .collect(Collectors.toList());
    }

    // Delete action
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        departmentRepository.delete(department);
    }
}