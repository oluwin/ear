package com.crown.employee.attendance_register.repository;

import com.crown.employee.attendance_register.model.Employee;
import com.crown.employee.attendance_register.model.enums.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByEmployeeType( EmployeeType employeeType); //MEDICAL OR NON-MEDICAL

    List<Employee> findByDepartmentId(Long departmentId);
}

