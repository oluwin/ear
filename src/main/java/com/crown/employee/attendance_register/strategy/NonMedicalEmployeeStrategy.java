package com.crown.employee.attendance_register.strategy;

import com.crown.employee.attendance_register.model.Employee;

public class NonMedicalEmployeeStrategy implements EmployeeStrategy {

    @Override
    public void performAction(Employee employee) {
        System.out.println("Registering non-medical employee-specific action for: " + employee.getFirstName() + " " + employee.getLastName());
    }
}