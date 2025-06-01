package service;

import model.Department;
import model.Employee;
import model.Organization;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class OrganizationService {
    private Organization organization;

    public OrganizationService() {
        this.organization = new Organization();
    }

    public void addDepartment(Department department) {
        organization.addDepartment(department);
    }

    public void addEmployee(Employee employee) {
        organization.addEmployee(employee);
    }

    public boolean removeEmployee(UUID employeeId) {
        for (Employee employee : organization.getEmployees()) {
            if (employee.getId().equals(employeeId)) {
                organization.removeEmployee(employee);
                return true;
            }
        }
        return false;
    }

    public Employee findEmployeeByName(String fullName) {
        for (Employee employee : organization.getEmployees()) {
            if (employee.getFullName().equalsIgnoreCase(fullName)) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> getEmployeesByDepartment(String departmentName) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : organization.getEmployees()) {
            if (employee.getDepartment() != null &&
                    employee.getDepartment().getName().equalsIgnoreCase(departmentName)) {
                result.add(employee);
            }
        }
        return result;
    }

    public Organization getOrganization() {
        return organization;
    }
}