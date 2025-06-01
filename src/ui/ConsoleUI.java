package ui;

import service.OrganizationService;
import model.Employee;
import model.Department;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private OrganizationService organizationService;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleUI(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    public void start() {
        while (true) {
            showMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": addEmployee(); break;
                case "2": removeEmployee(); break;
                case "3": searchEmployee(); break;
                case "4": showDepartments(); break;
                case "5": return; // Exit
                default: System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Добавить сотрудника");
        System.out.println("2. Удалить сотрудника");
        System.out.println("3. Найти сотрудника");
        System.out.println("4. Показать отделы");
        System.out.println("5. Выйти");
    }

    private void addEmployee() {
        System.out.println("Введите полное имя:");
        String fullName = scanner.nextLine();
        System.out.println("Введите дату рождения (дд.мм.гггг):");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine(), java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println("Введите пол (м/ж):");
        String gender = scanner.nextLine();
        System.out.println("Введите телефон:");
        String phone = scanner.nextLine();
        System.out.println("Введите должность:");
        String position = scanner.nextLine();
        System.out.println("Введите название отдела:");
        String departmentName = scanner.nextLine();
        Department department = findOrCreateDepartment(departmentName);
        System.out.println("Введите дату приема (дд.мм.гггг):");
        LocalDate hireDate = LocalDate.parse(scanner.nextLine(), java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        System.out.println("Введите зарплату:");
        double salary = Double.parseDouble(scanner.nextLine());

        Employee employee = new Employee(fullName, birthDate, gender, phone, position,
                department, null, hireDate, salary);
        organizationService.addEmployee(employee);
        System.out.println("Сотрудник добавлен: " + employee);
    }

    private void removeEmployee() {
        System.out.println("Введите полное имя сотрудника для удаления:");
        String fullName = scanner.nextLine();
        Employee employee = organizationService.findEmployeeByName(fullName);
        if (employee != null && organizationService.removeEmployee(employee.getId())) {
            System.out.println("Сотрудник удален: " + employee);
        } else {
            System.out.println("Сотрудник не найден.");
        }
    }

    private void searchEmployee() {
        System.out.println("Введите полное имя сотрудника для поиска:");
        String fullName = scanner.nextLine();
        Employee employee = organizationService.findEmployeeByName(fullName);
        if (employee != null) {
            System.out.println("Найденный сотрудник: " + employee);
        } else {
            System.out.println("Сотрудник не найден.");
        }
    }

    private Department findOrCreateDepartment(String departmentName) {
        for (Department department : organizationService.getDepartments()) {
            if (department.getName().equalsIgnoreCase(departmentName)) {
                return department;
            }
        }
        Department newDepartment = new Department(departmentName);
        organizationService.addDepartment(newDepartment);
        return newDepartment;
    }

    private void showDepartments() {
        List<Department> departments = organizationService.getDepartments();
        for (Department department : departments) {
            System.out.println(department);
        }
    }
}