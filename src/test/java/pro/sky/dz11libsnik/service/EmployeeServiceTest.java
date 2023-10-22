package pro.sky.dz11libsnik.service;

import org.junit.jupiter.api.Test;
import pro.sky.dz11libsnik.entity.Employee;
import pro.sky.dz11libsnik.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.dz11libsnik.service.utils.EmployeeGenerator.*;

class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private List<EmployeeService> employeeServiceList = new ArrayList<>();

    @Test
    void add_success() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee expectedEmployee = getEmployee();

        //Начало теста
        Employee actualEmployee = employeeService.add(firstName, lastName, salary, departmentId);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void add_withEmployeeStorageIsFullException() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salary2 = SALARY_2;

        String firstName3 = FIRST_NAME_3;
        String lastName3 = LAST_NAME_3;
        double salary3 = SALARY_3;

        //Подготовка ожидаемого результата
        String expectedMessage = "400 Массив сотрудников переполнен";

        //Начало теста
        employeeService.add(firstName2, lastName2, salary2, departmentId);
        employeeService.add(firstName3, lastName3, salary3, departmentId);
        Exception exception = assertThrows(
                EmployeeStorageIsFullException.class,
                () -> employeeService.add(firstName, lastName, salary, departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void find_success() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата

        Employee expectedEmployee = getEmployee();
        employeeService.add(firstName, lastName, salary, departmentId);


        //Начало теста
        Employee actualEmployee = employeeService.find(firstName, lastName, salary, departmentId);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void remove_success() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата

        Employee expectedEmployee = getEmployee();
        employeeService.add(firstName, lastName, salary, departmentId);


        //Начало теста
        Employee actualEmployee = employeeService.remove(firstName, lastName, salary, departmentId);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void getAll_success() {
        //Подготовка входных данных
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        double salary = SALARY;
        int departmentId = FIRST_DEPARTMENT_ID;

        String firstName2 = FIRST_NAME_2;
        String lastName2 = LAST_NAME_2;
        double salary2 = SALARY_2;


        //Подготовка ожидаемого результата

        List<Employee> expectedEmployee = getAllEmployee2();
        employeeService.add(firstName, lastName, salary, departmentId);
        employeeService.add(firstName2, lastName2, salary2, departmentId);


        //Начало теста
        List<Employee> actualEmployee = employeeService.getAll();
        assertEquals(expectedEmployee, actualEmployee);
    }
}