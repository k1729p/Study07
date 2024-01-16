package kp.company.domain;

/**
 * The simple DTO for the department
 *
 * @param departmentName the department name
 * @param employeesCount the count of the employees
 */
public record DepartmentDto(String departmentName, int employeesCount) {
}