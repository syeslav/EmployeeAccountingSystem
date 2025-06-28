import model.*;
import service.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final String EMP_FILE = "employees.dat";
    private static final String DEP_FILE = "department.dat";
    private static final String USER_FILE = "users.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Загрузка пользователей
        List<User> users;
        try {
            users = FileService.loadFromFile(USER_FILE);
        } catch (Exception e) {
            users = new ArrayList<>();
            users.add(new User("admin", "admin")); // по умолчанию
        }
        AuthService authService = new AuthService(users);

        // Авторизация
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Логин: ");
            String login = sc.nextLine();
            System.out.print("Пароль: ");
            String password = sc.nextLine();
            if (authService.login(login, password)) {
                loggedIn = true;
            } else {
                System.out.println("Неверный логин или пароль.");
            }
        }

        // Загрузка данных
        EmployeeService employeeService = new EmployeeService();
        DepartmentService departmentService = new DepartmentService();
        try {
            employeeService.setAll(FileService.loadFromFile(EMP_FILE));
            departmentService.setAll(FileService.loadFromFile(DEP_FILE));
        } catch (Exception ignored) {
        }

        ReportService reportService = new ReportService();

        // Меню
        while (true) {
            System.out.println("\n1. Принять сотрудника\n2. Уволить\n3. Изменить\n4. Поиск\n5. Отчеты\n6. Сохранить\n7. Выход");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    // Ввод данных сотрудника и добавление
                    acceptEmployee(sc, employeeService);
                    break;
                case 2:
                    // Удаление сотрудника
                    fireEmployee(sc, employeeService);
                    break;
                case 3:
                    // Изменение данных сотрудника
                    updateEmployee(sc, employeeService);
                    break;
                case 4:
                    // Поиск по ФИО/должности/отделу/начальнику
                    searchEmployee(sc, employeeService);
                    break;
                case 5:
                    // Генерация отчетов
                    generateReports(reportService, employeeService, departmentService);
                    break;
                case 6:
                    // Сохранение данных
                    saveData(employeeService, departmentService);
                    break;
                case 7:
                    return;
            }
        }
    }

    private static void acceptEmployee(Scanner sc, EmployeeService employeeService) {
        System.out.println("Введите ФИО сотрудника:");
        String fullName = sc.nextLine();
        System.out.println("Введите дату рождения (гггг-мм-дд):");
        LocalDate birthDate = LocalDate.parse(sc.nextLine());
        System.out.println("Введите пол (например, 'М' или 'Ж'):");
        String gender = sc.nextLine(); // Добавлено
        System.out.println("Введите контактный телефон:");
        String phone = sc.nextLine(); // Добавлено
        System.out.println("Введите дату приема на работу (гггг-мм-дд):");
        LocalDate hireDate = LocalDate.parse(sc.nextLine());
        System.out.println("Введите зарплату:");
        double salary = Double.parseDouble(sc.nextLine());
        System.out.println("Введите должность:");
        String position = sc.nextLine();
        System.out.println("Введите название отдела:");
        String departmentName = sc.nextLine();
        System.out.println("Введите ФИО начальника (если есть):");
        String managerFullName = sc.nextLine();

        Employee employee = new Employee(fullName, birthDate, gender, phone, position, departmentName, managerFullName, hireDate, salary);
        employeeService.add(employee);
        System.out.println("Сотрудник добавлен.");
    }

    private static void fireEmployee(Scanner sc, EmployeeService employeeService) {
        System.out.println("Введите ФИО сотрудника для увольнения:");
        String fullName = sc.nextLine();
        employeeService.remove(fullName);
        System.out.println("Сотрудник уволен.");
    }

    private static void updateEmployee(Scanner sc, EmployeeService employeeService) {
        System.out.println("Введите ФИО сотрудника для изменения:");
        String fullName = sc.nextLine();
        Employee employee = employeeService.searchByFullName(fullName).stream().findFirst().orElse(null);
        if (employee != null) {
            System.out.println("Введите новое ФИО (оставьте пустым для пропуска):");
            String newFullName = sc.nextLine();
            if (!newFullName.isEmpty()) {
                employee.setFullName(newFullName);
            }
            System.out.println("Введите новую зарплату (оставьте пустым для пропуска):");
            String salaryInput = sc.nextLine();
            if (!salaryInput.isEmpty()) {
                double salary = Double.parseDouble(salaryInput);
                employee.setSalary(salary);
            }
            System.out.println("Введите новую должность (оставьте пустым для пропуска):");
            String newPosition = sc.nextLine();
            if (!newPosition.isEmpty()) {
                employee.setPosition(newPosition);
            }
            System.out.println("Введите новое название отдела (оставьте пустым для пропуска):");
            String newDepartmentName = sc.nextLine();
            if (!newDepartmentName.isEmpty()) {
                employee.setDepartmentName(newDepartmentName);
            }
            System.out.println("Введите новое ФИО начальника (оставьте пустым для пропуска):");
            String newManagerFullName = sc.nextLine();
            if (!newManagerFullName.isEmpty()) {
                employee.setManagerFullName(newManagerFullName);
            }
            employeeService.update(employee);
            System.out.println("Данные сотрудника обновлены.");
        } else {
            System.out.println("Сотрудник не найден.");
        }
    }

    private static void searchEmployee(Scanner sc, EmployeeService employeeService) {
        System.out.println("Выберите способ поиска:\n1. По ФИО\n2. По должности\n3. По отделу\n4. По начальнику");
        int searchChoice = Integer.parseInt(sc.nextLine());
        List<Employee> results = new ArrayList<>();
        switch (searchChoice) {
            case 1:
                System.out.println("Введите ФИО для поиска:");
                String nameToSearch = sc.nextLine();
                results = employeeService.searchByFullName(nameToSearch);
                break;
            case 2:
                System.out.println("Введите должность для поиска:");
                String positionToSearch = sc.nextLine();
                results = employeeService.searchByPosition(positionToSearch);
                break;
            case 3:
                System.out.println("Введите название отдела для поиска:");
                String departmentToSearch = sc.nextLine();
                results = employeeService.searchByDepartment(departmentToSearch);
                break;
            case 4:
                System.out.println("Введите ФИО начальника для поиска:");
                String managerToSearch = sc.nextLine();
                results = employeeService.searchByManager(managerToSearch);
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }
        if (results.isEmpty()) {
            System.out.println("Сотрудники не найдены.");
        } else {
            System.out.println("Найденные сотрудники:");
            for (Employee employee : results) {
                System.out.println(employee);
            }
        }
    }

    private static void generateReports(ReportService reportService, EmployeeService employeeService, DepartmentService departmentService) {
        List<Employee> employees = employeeService.getAll();
        List<Department> departments = departmentService.getAll();

        System.out.println("1. Структура отдела.\n2. Средняя зарплата по всем сотрудникам.\n3. Средняя зарплата по отделам.\n4. ТОП-10 сотрудников по зарплате.\n5. ТОП-10 сотрудников по стажу работы.");
        int reportChoice = Integer.parseInt(new Scanner(System.in).nextLine());
        switch (reportChoice) {
            case 1:
                reportService.generateDepartmentStructure(departments);
                break;
            case 2:
                double averageSalary = reportService.avgSalary(employees);
                System.out.println("Средняя зарплата по всем сотрудникам: " + averageSalary);
                break;
            case 3:
                Map<String, Double> averageSalaryByDepartment = reportService.avgSalaryByDepartment(employees);
                System.out.println("Средняя зарплата по отделам: " + averageSalaryByDepartment);
                break;
            case 4:
                List<Employee> top10Employees = reportService.top10BySalary(employees);
                System.out.println("ТОП-10 сотрудников по зарплате:");
                for (Employee emp : top10Employees) {
                    System.out.println(" - " + emp.getFullName() + ": " + emp.getSalary());
                }
                break;
            case 5:
                List<Employee> top10ByLoyalty = reportService.top10ByLoyalty(employees);
                System.out.println("ТОП-10 сотрудников по стажу работы:");
                for (Employee emp: top10ByLoyalty) {
                    System.out.println(" - " + emp.getFullName() + ": " + emp.hireDate());
                }
                break;
            default:
                System.out.println("Неверный выбор отчета.");
        }
    }

    private static void saveData(EmployeeService employeeService, DepartmentService departmentService) {
        try {
            FileService.saveToFile(employeeService.getAll(), EMP_FILE);
            FileService.saveToFile(departmentService.getAll(), DEP_FILE);
            System.out.println("Данные сохранены.");
        } catch (Exception e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }
}