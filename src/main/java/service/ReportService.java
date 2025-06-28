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

    // Метод для установки списка отделов
    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    //  формирование структуры организации (список отделов и их начальников)
    public void generateDepartmentStructure(List<Department> departments) {
        for (Department d : departments) {
            System.out.println("Отдел: " + d.getName() + ", Начальник: " + d.getManagerFullName());
        }
    }

    //  расчет средней ЗП по всем сотрудникам
    public double avgSalary(List<Employee> employees) {
        return employees.stream().mapToDouble(Employee::getSalary).average().orElse(0);
    }

    //  расчет средней ЗП по каждому отделу
    public Map<String, Double> avgSalaryByDepartment(List<Employee> employees) {
        return employees.stream().
                collect(Collectors.groupingBy(
                        Employee::getDepartmentName, Collectors.averagingDouble(
                                Employee::getSalary)));
    }

    //  получение ТОП-10 соттрудников по ЗП
    public List<Employee> top10BySalary(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    //  получение ТОП-10 сотрудников по стажу работы
    public List<Employee> top10ByLoyalty(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparingLong(e -> -ChronoUnit.YEARS.between(e.getHireDate(), java.time.LocalDate.now() )))
                .limit(10)
                .collect(Collectors.toList());
    }

}