package com.crown.employee.attendance_register.data;

import com.crown.employee.attendance_register.model.enums.AttendanceType;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceResponseDto {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private LocalDate date;
    private LocalTime signInTime;
    private LocalTime signOutTime;
    private AttendanceType attendanceType;

    public AttendanceResponseDto( Long id, Long id1, LocalDate date, LocalTime signInTime, LocalTime signOutTime,
                                  String employeeName, AttendanceType attendanceType ) {
        this.id = id;
        this.employeeId = id1;
        this.date = date;
        this.signInTime = signInTime;
        this.signOutTime = signOutTime;
        this.employeeName = employeeName;
        this.attendanceType = attendanceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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
