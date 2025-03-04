package com.crown.employee.attendance_register.factory;

import com.crown.employee.attendance_register.model.Attendance;
import com.crown.employee.attendance_register.model.Employee;
import com.crown.employee.attendance_register.model.enums.AttendanceType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AttendanceFactory {

    // Create a new attendance data
    public static Attendance createAttendance(Employee employee, AttendanceType attendanceType, LocalDate date) {
        Attendance attendance = new Attendance();
        attendance.setEmployee(employee);
        attendance.setDate(date);
        attendance.setAttendanceType(attendanceType);

        switch (attendanceType) {
            case PRESENT:
                // Default
                attendance.setSignInTime(LocalTime.from(LocalDateTime.now()));
                break;
            case SICK_LEAVE:
            case ABSENCE:
            case AWOL:
                // No sign-in/sign-out for these types
                break;
            default:
                throw new IllegalArgumentException("Invalid attendance type: " + attendanceType);
        }

        return attendance;
    }

    // Update action
    public static Attendance updateAttendanceSignOut(Attendance attendance) {
        if (attendance.getAttendanceType() != AttendanceType.PRESENT) {
            throw new IllegalArgumentException("Sign-out is only allowed for PRESENT attendance records.");
        }

        attendance.setSignOutTime(LocalTime.from(LocalDateTime.now()));
        return attendance;
    }
}