package com.crown.employee.attendance_register.strategy;

import com.crown.employee.attendance_register.model.Attendance;

public class AttendanceContext {
    private final AttendanceCalculationStrategy strategy;

    public AttendanceContext(AttendanceCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public double executeStrategy(Attendance attendance) {
        return strategy.calculateAttendance(attendance);
    }
}