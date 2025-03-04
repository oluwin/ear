package com.crown.employee.attendance_register.strategy;

import com.crown.employee.attendance_register.model.Attendance;

public interface AttendanceCalculationStrategy {
    double calculateAttendance(Attendance attendance);
}