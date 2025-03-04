package com.crown.employee.attendance_register.service;

import com.crown.employee.attendance_register.data.EmployeeDto;
import com.crown.employee.attendance_register.data.EmployeeResponseDto;
import com.crown.employee.attendance_register.exception.DuplicateResourceException;
import com.crown.employee.attendance_register.exception.ResourceNotFoundException;
import com.crown.employee.attendance_register.factory.EmployeeFactory;
import com.crown.employee.attendance_register.model.Department;
import com.crown.employee.attendance_register.model.Employee;
import com.crown.employee.attendance_register.model.enums.EmployeeType;
import com.crown.employee.attendance_register.repository.DepartmentRepository;
import com.crown.employee.attendance_register.repository.EmployeeRepository;
import com.crown.employee.attendance_register.strategy.EmployeeContext;
import com.crown.employee.attendance_register.strategy.MedicalEmployeeStrategy;
import com.crown.employee.attendance_register.strategy.NonMedicalEmployeeStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // Convert Employee entity to EmployeeResponseDto
    private EmployeeResponseDto convertToEmployeeResponseDto(Employee employee) {
        return new EmployeeResponseDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getGender(),
                employee.getAddress(),
                employee.getDepartment().getName(),
                employee.getEmployeeType()
        );
    }

    // Add new employee
    public EmployeeResponseDto addEmployee(EmployeeDto employeeDTO) {
        // Check for duplicate entry
        employeeRepository.findByFirstNameAndLastName(employeeDTO.getFirstName(), employeeDTO.getLastName())
                .ifPresent(e -> {
                    throw new DuplicateResourceException("Employee with name " + employeeDTO.getFirstName() + " "
                            + employeeDTO.getLastName() + " already exists.");
                });

        // Confirm and Fetch Department Entity
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", employeeDTO.getDepartmentId()));

        // Create the Employee object using the Factory Pattern
        Employee employee = EmployeeFactory.createEmployee(
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getGender(),
                employeeDTO.getAddress(),
                department,
                employeeDTO.getEmployeeType()
        );

        // Save the employee
        Employee savedEmployee = employeeRepository.save(employee);

        // Convert to EmployeeResponseDTO and return
        return convertToEmployeeResponseDto(savedEmployee);
    }

    // Update action
    public EmployeeResponseDto updateEmployee(Long id, EmployeeDto employeeDTO) {
        // Confirm and Fetch existing Employee
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        // Check for duplicate entry, excluding the current employee being updated
        employeeRepository.findByFirstNameAndLastName(employeeDTO.getFirstName(), employeeDTO.getLastName())
                .ifPresent(existingEmployee -> {
                    if (!existingEmployee.getId().equals(id)) { // Ensure it's not the same employee
                        throw new DuplicateResourceException("Employee with name " + employeeDTO.getFirstName() + " "
                                + employeeDTO.getLastName() + " already exists.");
                    }
                });

        // Confirm and Fetch Department entity
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", employeeDTO.getDepartmentId()));

        // Update Employee object
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setGender(employeeDTO.getGender());
        employee.setAddress(employeeDTO.getAddress());
        employee.setDepartment(department);
        employee.setEmployeeType(employeeDTO.getEmployeeType());

        // Save the updated employee
        Employee updatedEmployee = employeeRepository.save(employee);

        // Convert to EmployeeResponseDTO and return
        return convertToEmployeeResponseDto(updatedEmployee);
    }

    // Fetch all employees
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToEmployeeResponseDto)
                .collect(Collectors.toList());
    }

    // Fetch employees by department
    public List<EmployeeResponseDto> getEmployeesByDepartmentId(Long departmentId) {
        departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id",departmentId));

        return employeeRepository.findByDepartmentId(departmentId)
                .stream()
                .map(this::convertToEmployeeResponseDto)
                .collect(Collectors.toList());
    }

    // Fetch employee by ID
    public EmployeeResponseDto getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(this::convertToEmployeeResponseDto)
                .orElseThrow(() -> new RuntimeException("Employee with ID: "+ employeeId +" not found!" ));
    }

    // Fetch employees by type (MEDICAL or NON_MEDICAL)
    public List<EmployeeResponseDto> getEmployeesByType(EmployeeType employeeType) {
        if (employeeType == null || (employeeType != EmployeeType.MEDICAL && employeeType != EmployeeType.NON_MEDICAL)) {
            throw new IllegalArgumentException("Employee type must be either MEDICAL or NON_MEDICAL.");
        }
        return employeeRepository.findByEmployeeType(employeeType)
                .stream()
                .map(this::convertToEmployeeResponseDto)
                .collect(Collectors.toList());
    }

    // Perform employee-specific action using the Strategy Pattern
    public void performEmployeeAction(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        EmployeeContext context;

        if (employee.getEmployeeType() == EmployeeType.MEDICAL) {
            context = new EmployeeContext(new MedicalEmployeeStrategy());
        } else if (employee.getEmployeeType() == EmployeeType.NON_MEDICAL) {
            context = new EmployeeContext(new NonMedicalEmployeeStrategy());
        } else {
            throw new IllegalArgumentException("Invalid employee type.");
        }

        context.executeStrategy(employee);
    }
}