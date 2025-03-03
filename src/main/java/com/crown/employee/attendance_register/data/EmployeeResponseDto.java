package com.crown.employee.attendance_register.data;

import com.crown.employee.attendance_register.model.enums.EmployeeType;

public class EmployeeResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private EmployeeType employeeType;
    private String departmentName;

    public EmployeeResponseDto( Long id, String firstName, String lastName, String gender, String address, Long id1,
                                EmployeeType employeeType ) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
