package service;

import model.Employee;
import model.Department;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportService {
    private List<Department> departments;


    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }


    public void generateDepartmentStructure(List<Department> departments) {
        for (Department d : departments) {
            System.out.println("Отдел: " + d.getName() + ", Начальник: " + d.getManagerFullName());
        }
    }


    public double avgSalary(List<Employee> employees) {
        return employees.stream().mapToDouble(Employee::getSalary).average().orElse(0);
    }


    public Map<String, Double> avgSalaryByDepartment(List<Employee> employees) {
        return employees.stream().
                collect(Collectors.groupingBy(
                        Employee::getDepartmentName, Collectors.averagingDouble(
                                Employee::getSalary)));
    }


    public List<Employee> top10BySalary(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }


    public List<Employee> top10ByLoyalty(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingLong(e -> -ChronoUnit.YEARS.between(e.getHireDate(), java.time.LocalDate.now() )))
                .limit(10)
                .collect(Collectors.toList());
    }

}