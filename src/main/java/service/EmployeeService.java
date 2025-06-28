package service;



import model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {
    private List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>(); // Инициализация списка
    }


    public void add(Employee emp) {
        employees.add(emp);
    }


    public void remove(String fullName) {
        employees.removeIf(e -> e.getFullName().equals(fullName));
    }


    public void update(Employee updatedEmp) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFullName().equals(updatedEmp.getFullName())) {
                employees.set(i, updatedEmp);
                break;
            }
        }
    }


    public List<Employee> searchByFullName(String name) {
        return employees.stream()
                .filter(e -> e.getFullName() != null && e.getFullName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }


    public List<Employee> searchByPosition(String position) {
        return employees.stream()
                .filter(e -> e.getPosition().equalsIgnoreCase(position))
                .collect(Collectors.toList());
    }


    public List<Employee> searchByDepartment(String department) {
        return employees.stream()
                .filter(e -> e.getDepartmentName().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }


    public List<Employee> searchByManager(String manager) {
        return employees.stream()
                .filter(e -> e.getManagerFullName() != null && e.getManagerFullName().equalsIgnoreCase(manager))
                .collect(Collectors.toList());
    }


    public List<Employee> getAll() {
        return employees;
    }


    public void setAll(List<Employee> employees) {
        this.employees = employees;
    }

}