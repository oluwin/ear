package com.crown.employee.attendance_register.strategy;

import com.crown.employee.attendance_register.model.Employee;

public class EmployeeContext {
    private EmployeeStrategy strategy;

    public EmployeeContext(EmployeeStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Employee employee) {
        strategy.performAction(employee);
    }
}