package com.crown.employee.attendance_register.repository;

import com.crown.employee.attendance_register.model.Attendance;
import com.crown.employee.attendance_register.model.enums.AttendanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a WHERE a.employee.id = :employeeId AND a.date BETWEEN :startDate AND :endDate")
    List<Attendance> findByEmployeeIdAndDateBetween(
            @Param("employeeId") Long employeeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    List<Attendance> findByEmployeeId(Long employeeId);

    List<Attendance> findByAttendanceType( AttendanceType attendanceType );

    List<Attendance> findByEmployeeIdAndAttendanceType( Long employeeId, AttendanceType attendanceType );

    List<Attendance> findByDateBetween( LocalDate startDate, LocalDate endDate );
}