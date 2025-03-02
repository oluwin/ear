package com.crown.employee.attendance_register.model;

import com.crown.employee.attendance_register.model.enums.AttendanceType;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate date;

    private LocalTime signInTime;
    private LocalTime signOutTime;

    @Enumerated(EnumType.STRING)
    private AttendanceType attendanceType;

    public Attendance() {
    }

    public Attendance(Employee employee, LocalDate date, LocalTime signInTime, LocalTime signOutTime, AttendanceType attendanceType) {
        this.employee = employee;
        this.date = date;
        this.signInTime = signInTime;
        this.signOutTime = signOutTime;
        this.attendanceType = attendanceType;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", employee=" + employee.getFirstName() + " " + employee.getLastName() +
                ", date=" + date +
                ", signInTime=" + signInTime +
                ", signOutTime=" + signOutTime +
                ", attendanceType=" + attendanceType +
                '}';
    }
}
