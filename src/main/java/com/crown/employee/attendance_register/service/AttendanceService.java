package com.crown.employee.attendance_register.service;

import com.crown.employee.attendance_register.data.AttendanceDto;
import com.crown.employee.attendance_register.data.AttendanceResponseDto;
import com.crown.employee.attendance_register.exception.ResourceNotFoundException;
import com.crown.employee.attendance_register.factory.AttendanceFactory;
import com.crown.employee.attendance_register.model.Attendance;
import com.crown.employee.attendance_register.model.Employee;
import com.crown.employee.attendance_register.model.enums.AttendanceType;
import com.crown.employee.attendance_register.model.enums.EmployeeType;
import com.crown.employee.attendance_register.repository.AttendanceRepository;
import com.crown.employee.attendance_register.repository.EmployeeRepository;
import com.crown.employee.attendance_register.strategy.AttendanceCalculationStrategy;
import com.crown.employee.attendance_register.strategy.AttendanceContext;
import com.crown.employee.attendance_register.strategy.MedicalEmployeeAttendanceStrategy;
import com.crown.employee.attendance_register.strategy.NonMedicalEmployeeAttendanceStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Helper method to convert Attendance entity to AttendanceResponseDto
    private AttendanceResponseDto convertToAttendanceResponseDto(Attendance attendance) {
        return new AttendanceResponseDto(
                attendance.getId(),
                attendance.getEmployee().getId(),
                attendance.getDate(),
                attendance.getSignInTime(),
                attendance.getSignOutTime(),
                attendance.getEmployee().getFirstName() + " " + attendance.getEmployee().getLastName(),
                attendance.getAttendanceType()
        );
    }

    // Mark attendance (sign-in)
    public AttendanceResponseDto recordSignIn(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        Attendance attendance = AttendanceFactory.createAttendance(employee, AttendanceType.PRESENT, LocalDate.now());
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return convertToAttendanceResponseDto(savedAttendance);
    }

    // Update attendance (sign-out)
    public AttendanceResponseDto recordSignOut(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance", "id", attendanceId));

        Attendance updatedAttendance = AttendanceFactory.updateAttendanceSignOut(attendance);
        Attendance savedAttendance = attendanceRepository.save(updatedAttendance);
        return convertToAttendanceResponseDto(savedAttendance);
    }

    // Register sick leave
    public AttendanceResponseDto recordSickLeave(Long employeeId, LocalDate date) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        Attendance attendance = AttendanceFactory.createAttendance(employee, AttendanceType.SICK_LEAVE, date);
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return convertToAttendanceResponseDto(savedAttendance);
    }

    // Register absence
    public AttendanceResponseDto recordAbsence(Long employeeId, LocalDate date) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        Attendance attendance = AttendanceFactory.createAttendance(employee, AttendanceType.ABSENCE, date);
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return convertToAttendanceResponseDto(savedAttendance);
    }

    // Register AWOL (Absence Without Leave)
    public AttendanceResponseDto recordAWOL(Long employeeId, LocalDate date) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        Attendance attendance = AttendanceFactory.createAttendance(employee, AttendanceType.AWOL, date);
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return convertToAttendanceResponseDto(savedAttendance);
    }

    // Generate attendance report for an employee within a date range
    public List<AttendanceResponseDto> generateAttendanceReport(Long employeeId, LocalDate startDate, LocalDate endDate) {
        // Confirm if employee exists
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        // Fetch attendance records for the employee within the date range
        return attendanceRepository.findByEmployeeIdAndDateBetween(employeeId, startDate, endDate)
                .stream()
                .map(this::convertToAttendanceResponseDto)
                .collect(Collectors.toList());
    }

    // Generate attendance report for all employees within a date range
    public List<AttendanceResponseDto> generateAllEmployeesAttendanceReport(LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findByDateBetween(startDate, endDate)
                .stream()
                .map(this::convertToAttendanceResponseDto)
                .collect(Collectors.toList());
    }

    // Generate attendance summary for an employee within a date range
    public String generateAttendanceSummary(Long employeeId, LocalDate startDate, LocalDate endDate) {
        List<AttendanceResponseDto> attendanceRecords = generateAttendanceReport(employeeId, startDate, endDate);

        // Confirm and Fetch the employee to determine their type (MEDICAL or NON_MEDICAL)
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        // Use the appropriate strategy to calculate hours worked
        AttendanceCalculationStrategy strategy = getAttendanceCalculationStrategy(employee.getEmployeeType());
        double totalManPowerHoursAmount = attendanceRecords.stream()
                .mapToDouble(attendance -> strategy.calculateAttendance(convertToAttendanceEntity(attendance)))
                .sum();

        // Generate summary
        String empName = employee.getFirstName() + " " + employee.getLastName();

        return String.format(
                "Attendance Summary for Employee ID %s (%s - %s): " +
                        "Total Records: %d - " +
                        "Total Man-Hours Amount: %.2f",
                empName, startDate, endDate, attendanceRecords.size(), totalManPowerHoursAmount
        );
    }

    // Convert AttendanceResponseDto to Attendance entity
    private Attendance convertToAttendanceEntity(AttendanceResponseDto attendanceResponseDto) {
        Attendance attendance = new Attendance();
        attendance.setId(attendanceResponseDto.getId());
        attendance.setEmployee(employeeRepository.findById(attendanceResponseDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", attendanceResponseDto.getEmployeeId())));
        attendance.setDate(attendanceResponseDto.getDate());
        attendance.setSignInTime(attendanceResponseDto.getSignInTime());
        attendance.setSignOutTime(attendanceResponseDto.getSignOutTime());
        attendance.setAttendanceType(attendanceResponseDto.getAttendanceType());
        return attendance;
    }

    // Get the appropriate strategy based on employee type
    private AttendanceCalculationStrategy getAttendanceCalculationStrategy(EmployeeType employeeType) {
        if (employeeType == EmployeeType.MEDICAL) {
            return new MedicalEmployeeAttendanceStrategy();
        } else if (employeeType == EmployeeType.NON_MEDICAL) {
            return new NonMedicalEmployeeAttendanceStrategy();
        } else {
            throw new IllegalArgumentException("Invalid employee type.");
        }
    }

    // Fetch attendance by type
    public List<AttendanceResponseDto> getAttendanceByType(AttendanceType attendanceType) {
        return attendanceRepository.findByAttendanceType(attendanceType)
                .stream()
                .map(this::convertToAttendanceResponseDto)
                .collect(Collectors.toList());
    }

    // Fetch attendance by employee and type
    public List<AttendanceResponseDto> getAttendanceByEmployeeAndType(Long employeeId, AttendanceType attendanceType) {
        return attendanceRepository.findByEmployeeIdAndAttendanceType(employeeId, attendanceType)
                .stream()
                .map(this::convertToAttendanceResponseDto)
                .collect(Collectors.toList());
    }

    // Delete attendance
    public void deleteAttendance(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance", "id", attendanceId));
        attendanceRepository.delete(attendance);
    }

    // Calculate attendance for an employee
    public double calculateAttendance(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance", "id", attendanceId));

        Employee employee = attendance.getEmployee();
        AttendanceContext context;

        if (employee.getEmployeeType() == EmployeeType.MEDICAL) {
            context = new AttendanceContext(new MedicalEmployeeAttendanceStrategy());
        } else if (employee.getEmployeeType() == EmployeeType.NON_MEDICAL) {
            context = new AttendanceContext(new NonMedicalEmployeeAttendanceStrategy());
        } else {
            throw new IllegalArgumentException("Invalid employee type.");
        }

        return context.executeStrategy(attendance);
    }
}