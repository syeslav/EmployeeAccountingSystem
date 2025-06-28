package service;

import model.Department;

import java.util.ArrayList;
import java.util.List;



public class DepartmentService {
    private List<Department> departments;


    public DepartmentService() {
        this.departments = new ArrayList<>();
    }


    public void addDepartment(Department dep) {
        if (dep != null) {
            departments.add(dep);
        }
    }


    public List<Department> getAll() {
        return departments;
    }


    public void setAll(List<Department> departments) {
        this.departments = departments;
    }
}