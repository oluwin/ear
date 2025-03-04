package com.crown.employee.attendance_register.factory;

import com.crown.employee.attendance_register.model.Department;
import com.crown.employee.attendance_register.model.Employee;
import com.crown.employee.attendance_register.model.enums.EmployeeType;

public class EmployeeFactory {

    // Create Employee object
    public static Employee createEmployee(
            String firstName,
            String lastName,
            String gender,
            String address,
            Department department,
            EmployeeType employeeType
    ) {
        if ((employeeType != EmployeeType.MEDICAL && employeeType != EmployeeType.NON_MEDICAL)) {
            throw new IllegalArgumentException("Employee type must be either MEDICAL or NON_MEDICAL.");
        }

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setGender(gender);
        employee.setAddress(address);
        employee.setDepartment(department);
        employee.setEmployeeType(employeeType);

        return employee;
    }
}
