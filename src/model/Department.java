package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Department implements Serializable {
    private String name;                    // название отдела
    private Employee head;                  // начальник отдела
    private List <Employee> employees;      // сотрудник отдела

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    // Getters and setters
    public String getName() { return name; }
    public Employee getHead() { return head; }
    public List<Employee> getEmployees() { return employees; }

    public void setHead(Employee head) { this.head = head; }
    public void addEmployee(Employee employee) { employees.add(employee); }
    public void removeEmployee(Employee employee) { employees.remove(employee); }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", head=" + (head != null ? head.getFullName() : "None") +
                ", employees=" + employees.size() +
                '}';
    }
}
