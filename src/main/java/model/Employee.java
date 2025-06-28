package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

//  Клас для хранения информации о сотруднике.

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fullName;        // ФИО сотрудника.
    private LocalDate birthDate;    // Дата рождения.
    private String gender;          // Пол.
    private String phone;           // Контактный телефон.
    private String position;        // Должность.
    private String departmentName;  // Название отдела.
    private String managerFullName; // ФИО начальника.
    private LocalDate hireDate;     // Дата приема на работу.
    private double salary;          // Зарплата.

    // Конструктор
    public Employee(String fullName, LocalDate birthDate, String gender, String phone, String position,
                    String departmentName, String managerFullName, LocalDate hireDate, double salary) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.position = position;
        this.departmentName = departmentName;
        this.managerFullName = managerFullName;
        this.hireDate = hireDate;
        this.salary = salary;
    }


    // Геттеры
    public String fullName() {
        return fullName;
    }
    public LocalDate birthDate() {
        return birthDate;
    }
    public String gender() {
        return gender;
    }
    public String phone() {
        return phone;
    }
    public String position() {
        return position;
    }
    public String departmentName() {
        return departmentName;
    }
    public String managerFullName() {
        return managerFullName;
    }
    public LocalDate hireDate() {
        return hireDate;
    }
    public double salary() {
        return salary;
    }

    // Сеттеры
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public void setManagerFullName(String managerFullName) {
        this.managerFullName = managerFullName;
    }
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getYearsOfService() {
        return Period.between(hireDate, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return String.format("ФИО: %s, Должность: %s, Отдел: %s, Начальник: %s, Зарплата: %.2f, Стаж: %d лет",
                fullName, position,
                (departmentName!= null &&!departmentName.isEmpty()? departmentName : "Нет"),
                (managerFullName!= null &&!managerFullName.isEmpty()? managerFullName : "Нет"),
                salary, getYearsOfService());
    }


    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getManagerFullName() {
        return managerFullName;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }
}