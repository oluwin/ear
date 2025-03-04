package com.crown.employee.attendance_register.strategy;

import com.crown.employee.attendance_register.model.Employee;

public class MedicalEmployeeStrategy implements EmployeeStrategy {

    @Override
    public void performAction(Employee employee) {
        System.out.println("Registering medical employee-specific action for: " + employee.getFirstName() + " " + employee.getLastName());
    }
}