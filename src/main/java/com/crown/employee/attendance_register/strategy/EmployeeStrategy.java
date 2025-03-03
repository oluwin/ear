package com.crown.employee.attendance_register.strategy;

import com.crown.employee.attendance_register.model.Employee;

public interface EmployeeStrategy {
    void performAction(Employee employee);
}