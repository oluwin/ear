package com.crown.employee.attendance_register.data;

import com.crown.employee.attendance_register.model.enums.AttendanceType;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceDto {
    private Long employeeId;
    private LocalDate date;
    private LocalTime signInTime;
    private LocalTime signOutTime;
    private AttendanceType attendanceType;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(LocalTime signInTime) {
        this.signInTime = signInTime;
    }

    public LocalTime getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(LocalTime signOutTime) {
        this.signOutTime = signOutTime;
    }

    public AttendanceType getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
    }
}
