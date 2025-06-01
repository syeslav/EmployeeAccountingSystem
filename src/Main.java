import model.Department;
import model.Employee;
import service.OrganizationService;
import service.StorageService;
import ui.ConsoleUI;

import java.io.File;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        OrganizationService orgService = new OrganizationService();

        // Загрузка данных из файла, если он существует
        File file = new File("organization.dat");
        if (file.exists()) {
            orgService = StorageService.loadOrganization("organization.dat");
        } else {
            // Создание тестовых данных при первом запуске
            Department itDepartment = new Department("IT");
            orgService.addDepartment(itDepartment);
            orgService.addEmployee(new Employee("Иванов Иван Иванович", LocalDate.of(1985, 5, 20), "м",
                    "89261234567", "Программист", itDepartment, null, LocalDate.now(), 60000));
        }

        // Запуск консольного интерфейса
        ConsoleUI consoleUI = new ConsoleUI(orgService);
        consoleUI.start();

        // Сохранение данных перед выходом
        StorageService.saveOrganization(orgService, "organization.dat");
    }
}