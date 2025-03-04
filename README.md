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
   - Record sick leave, absence, and AWOL etc.
   - Generate attendance reports for a specific employee or all employees within a date range.
   - Generate an attendance summary for an employee within a date range.

3. **Database**:
   - Uses **PostgreSQL** for data storage.
   - Automatically creates tables using Hibernate.

4. **API Documentation**:
   - RESTful APIs with consistent status codes and responses.

---

## **Documentation**

For detailed instructions, please refer to the following documents:

- **[Setup Guide](docs/setup.md)**: How to set up and run the project.
- **[API Endpoints](docs/api-endpoints.md)**: List of all available API endpoints.
- **[Database Schema](docs/database-schema.md)**: Details about the database tables.
- **[Contributing](docs/contributing.md)**: Guidelines for contributing to the project.
- **[License](docs/license.md)**: License information.

---

## **Technologies Used**

- **Java 17**
- **Spring Boot 3.x**
- **PostgreSQL** (You may use any db of choice)
- **Docker** (for containerization)
- **Maven** (for dependency management)

---

## **Contact**

For any questions or feedback, please contact:

- **Adebumiti A. Olusegun**  
- **Email**: connect2olusegun@gmail.com  
- **GitHub**: [oluwin](https://github.com/oluwin)
