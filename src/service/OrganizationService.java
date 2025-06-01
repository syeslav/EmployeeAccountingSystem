package service;

import model.Department;
import model.Employee;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class OrganizationService {
    private List<Department> departments;
    private List<Employee> employees;

    public OrganizationService() {
        this.departments = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        if (employee.getDepartment() != null) {
            employee.getDepartment().addEmployee(employee);
        }
    }

    public boolean removeEmployee(UUID employeeId) {
        for (Employee employee : employees) {
            if (employee.getId().equals(employeeId)) {
                employees.remove(employee);
                if (employee.getDepartment() != null) {
                    employee.getDepartment().removeEmployee(employee);
                }
                return true;
            }
        }
        return false;
    }

    public Employee findEmployeeByName(String fullName) {
        for (Employee employee : employees) {
            if (employee.getFullName().equalsIgnoreCase(fullName)) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> getEmployeesByDepartment(String departmentName) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getDepartment() != null &&
                    employee.getDepartment().getName().equalsIgnoreCase(departmentName)) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Department> getDepartments() {
        return departments;
    }
}