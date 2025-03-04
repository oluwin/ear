# Employee Attendance Register

This is a Spring Boot application for managing employee attendance in a high-tech medical organization. It allows you to add employees, filter employees, record attendance (sign-in/sign-out), manage sick leave and other attendance types like absence, AWOL (Absent Without Leave) and any other as the need arises. The application also provides endpoints to generate attendance reports and summaries.

---

## **Features**

1. **Employee Management**:
   - Add a new employee.
   - Modify an existing employee.
   - Fetch all employees or filter by department.

2. **Attendance Management**:
   - Record attendance (sign-in/sign-out).
   - Record sick leave, absence, and AWOL.
   - Generate attendance reports for a specific employee or all employees within a date range.
   - Generate an attendance summary for an employee within a date range.

3. **Database**:
   - Uses **PostgreSQL** for data storage.
   - Automatically creates tables using Hibernate.

4. **Status Codes**:
   - RESTful APIs with consistent status codes and responses.

---

## **Technologies Used**

- **Java 17**
- **Spring Boot 3.x**
- **PostgreSQL**
- **Docker** (for containerization)
- **Maven** (for dependency management)

---

## **Setup and Installation**

### **Prerequisites**

1. **Java 17**: Ensure you have Java 17 installed.
2. **PostgreSQL**: Install and run PostgreSQL locally or in a Docker container.
3. **Docker** (optional): If you want to run the application in a Docker container.

### **Steps to Run the Application**

#### **1. Clone the Repository**
```bash
git clone https://github.com/oluwin/ear.git
cd ear
