package com.crown.employee.attendance_register.strategy;

import com.crown.employee.attendance_register.model.Attendance;

import java.time.Duration;

public class NonMedicalEmployeeAttendanceStrategy implements AttendanceCalculationStrategy {

    @Override
    public double calculateAttendance(Attendance attendance) {
        System.out.println("Calculating attendance for non-medical employee: " + attendance.getEmployee().getFirstName() + " " + attendance.getEmployee().getLastName());

        // Eg: Calculate attendance based on non-medical-specific rules
        double hoursWorked = calculateHoursWorked(attendance);
        return hoursWorked * 450; // Non-medical employees get NGN 800 per hour
    }

    private double calculateHoursWorked(Attendance attendance) {

        if (attendance.getSignInTime() == null || attendance.getSignOutTime() == null) {
            return 0.0;
        }
        return Duration.between(attendance.getSignInTime(), attendance.getSignOutTime()).toHours();
    }
}