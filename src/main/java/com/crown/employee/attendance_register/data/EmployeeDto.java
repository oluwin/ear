package com.crown.employee.attendance_register.data;

import com.crown.employee.attendance_register.model.enums.EmployeeType;

public class EmployeeDto {
        private String firstName;
        private String lastName;
        private String gender;
        private String address;
        private EmployeeType employeeType;
        private Long departmentId;

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

        public Long getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Long departmentId) {
            this.departmentId = departmentId;
        }
}