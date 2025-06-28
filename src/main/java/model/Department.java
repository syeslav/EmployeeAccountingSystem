package model;

import java.io.Serializable;


public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String managerFullName;

    public Department(String name, String managerFullName) {
        this.name = name;
        this.managerFullName = managerFullName;
    }


    public String name() {
        return name;
    }
    public String managerFullName() {
        return managerFullName;
    }


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