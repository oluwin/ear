package com.crown.employee.attendance_register.controller;

import com.crown.employee.attendance_register.data.ResponseDto;
import com.crown.employee.attendance_register.data.AttendanceDto;
import com.crown.employee.attendance_register.data.AttendanceResponseDto;
import com.crown.employee.attendance_register.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Record attendance (sign-in)
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<AttendanceResponseDto> recordSignIn(@RequestBody AttendanceDto attendanceDto) {
        AttendanceResponseDto attendanceResponseDto = attendanceService.recordSignIn(attendanceDto.getEmployeeId());
        return ResponseDto.success("Attendance recorded successfully", attendanceResponseDto);
    }

    // Record attendance (sign-out)
    @PostMapping("/sign-out/{attendanceId}")
    public ResponseDto<AttendanceResponseDto> recordSignOut(@PathVariable Long attendanceId) {
        AttendanceResponseDto attendanceResponseDto = attendanceService.recordSignOut(attendanceId);
        return ResponseDto.success("Attendance updated successfully", attendanceResponseDto);
    }

    // Generate attendance report for an employee within a date range
    @GetMapping("/report/employee/{employeeId}")
    public ResponseDto<List<AttendanceResponseDto>> generateAttendanceReport(
            @PathVariable Long employeeId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        List<AttendanceResponseDto> attendanceReport = attendanceService.generateAttendanceReport(
                employeeId,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate)
        );
        return ResponseDto.success("Attendance report generated successfully", attendanceReport);
    }

    // Generate attendance report for all employees within a date range
    @GetMapping("/report/all")
    public ResponseDto<List<AttendanceResponseDto>> generateAllEmployeesAttendanceReport(
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        List<AttendanceResponseDto> attendanceReport = attendanceService.generateAllEmployeesAttendanceReport(
                LocalDate.parse(startDate),
                LocalDate.parse(endDate)
        );
        return ResponseDto.success("Attendance report generated successfully", attendanceReport);
    }

    // Generate attendance summary for an employee within a date range
    @GetMapping("/summary/employee/{employeeId}")
    public ResponseDto<String> generateAttendanceSummary(
            @PathVariable Long employeeId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        String summary = attendanceService.generateAttendanceSummary(
                employeeId,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate)
        );
        return ResponseDto.success("Attendance summary generated successfully", summary);
    }
}