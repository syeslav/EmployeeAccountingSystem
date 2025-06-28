package model;

import java.io.Serializable;

//  Класс для хранения информации об отдел.

public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;                // Название отдела. Должно быть уникальным.
    private String managerFullName;     // ФИО начальника отдела

    // Конструктор
    public Department(String name, String managerFullName) {
        this.name = name;
        this.managerFullName = managerFullName;
    }

    // Геттеры
    public String name() {
        return name;
    }
    public String managerFullName() {
        return managerFullName;
    }

    // Сеттеры
    public void setName(String name) {
        this.name = name;
    }
    public void setManagerFullName(String managerFullName) {
        this.managerFullName = managerFullName;
    }

    @Override
    public String toString() {
        return "Отдел: " + name + ", Начальник: " +
                (managerFullName!= null &&!managerFullName.isEmpty()? managerFullName : "Не назначен");
    }

    public String getName() {
        return name;
    }

    public String getManagerFullName() {
        return managerFullName;
    }
}