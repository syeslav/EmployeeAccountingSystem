package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Employee implements Serializable {
    private UUID id;                // уникальный идентификатор
    private String fullName;        // ФИО
    private LocalDate birthDate;    // дата рождения
    private String gender;          // пол
    private String phone;           // контактный телефон
    private String position;        // должность
    private Department department;  // отдел
    private Employee manager;        // начальник
    private LocalDate hireDate;     // дата приема на работу
    private  double salary;         // зарплата

    public Employee(String fullName, LocalDate birthDate, String gender, String phone,
                    String position, Department department, Employee manager,
                    LocalDate hireDate, double salary) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.position = position;
        this.department = department;
        this.manager = manager;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    // Getters and setters
    public UUID getId() { return id; }
    public String getFullName() { return fullName; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getGender() { return gender; }
    public String getPhone() { return phone; }
    public String getPosition() { return position; }
    public Department getDepartment() { return department; }
    public Employee getManager() { return manager; }
    public LocalDate getHireDate() { return hireDate; }
    public double getSalary() { return salary; }

    public void setDepartment(Department department) { this.department = department; }
    public void setManager(Employee manager) { this.manager = manager; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return String.format("Employee{id=%s, fullName='%s', birthDate=%s, gender='%s', " +
                        "phone='%s', position='%s', department='%s', manager='%s', " +
                        "hireDate=%s, salary=%.2f}",
                id, fullName, birthDate, gender, phone, position,
                department != null ? department.getName() : "None",
                manager != null ? manager.getFullName() : "None", hireDate, salary);
    }
}


