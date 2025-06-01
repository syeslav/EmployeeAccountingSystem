package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Organization implements Serializable {
    private List<Department> departments;
    private List<Employee> employees;

    public Organization() {
        this.departments = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<Employee> getEmployees() {
        return employees;
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

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        if (employee.getDepartment() != null) {
            employee.getDepartment().removeEmployee(employee);
        }
    }

    @Override
    public String toString() {
        return "Organization{" +
                "departments=" + departments +
                ", employees=" + employees.size() +
                '}';
    }
}