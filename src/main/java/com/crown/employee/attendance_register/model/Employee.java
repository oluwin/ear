package com.crown.employee.attendance_register.model;

import com.crown.employee.attendance_register.model.enums.EmployeeType;
import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private String address;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;  // Many employees belong to one department

    public Employee() {
    }

    // Parameterized constructor
    public Employee(String firstName, String lastName, String gender, String address, EmployeeType employeeType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.employeeType = employeeType;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", employeeType=" + employeeType +
                ", department=" + (department != null ? department.getName() : "None") +
                '}';
    }
}