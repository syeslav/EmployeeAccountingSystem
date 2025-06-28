package service;

//  Класс для управления сотрудниками.

import model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {
    private List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>(); // Инициализация списка
    }

    // добавить нового сотрудника в список.
    public void add(Employee emp) {
        employees.add(emp);
    }

    // уволить сотрудника по ФИО.
    public void remove(String fullName) {
        employees.removeIf(e -> e.getFullName().equals(fullName));
    }

    // обновить информацию о сотруднике (поиск по ФИО, замена на новые данныe).
    public void update(Employee updatedEmp) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getFullName().equals(updatedEmp.getFullName())) {
                employees.set(i, updatedEmp);
                break;
            }
        }
    }

    // найти сотрудника по ФИО (частичное совпадение).
    public List<Employee> searchByFullName(String name) {
        return employees.stream()
                .filter(e -> e.getFullName() != null && e.getFullName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // найти сотрудника по должности.
    public List<Employee> searchByPosition(String position) {
        return employees.stream()
                .filter(e -> e.getPosition().equalsIgnoreCase(position))
                .collect(Collectors.toList());
    }

    // найти сотрудника по отделу.
    public List<Employee> searchByDepartment(String department) {
        return employees.stream()
                .filter(e -> e.getDepartmentName().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    // найти сотрудника по ФИО начальника.
    public List<Employee> searchByManager(String manager) {
        return employees.stream()
                .filter(e -> e.getManagerFullName() != null && e.getManagerFullName().equalsIgnoreCase(manager))
                .collect(Collectors.toList());
    }

    // получить список всех сотрудников.
    public List<Employee> getAll() {
        return employees;
    }

    // установить (заменить) список сотрудников.
    public void setAll(List<Employee> employees) {
        this.employees = employees;
    }

}