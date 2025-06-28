package service;

import model.Department;

import java.util.ArrayList; // Импортируем ArrayList
import java.util.List;

// Класс для хранения информации об отделе.

public class DepartmentService {
    private List<Department> departments;

    // Добавляем конструктор для инициализации списка
    public DepartmentService() {
        this.departments = new ArrayList<>();
    }

    // добавить новый отдел.
    public void addDepartment(Department dep) {
        // Добавь проверку на null, если хочешь убедиться, что отдел не null
        if (dep != null) {
            departments.add(dep);
        }
    }

    // получить список всех отделов.
    public List<Department> getAll() {
        return departments;
    }

    // установить (заменить) список отделов.
    public void setAll(List<Department> departments) {
        this.departments = departments;
    }
}