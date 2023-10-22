package pro.sky.dz11libsnik.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.dz11libsnik.entity.Employee;
import pro.sky.dz11libsnik.exception.EmployeeAlreadyAddedException;
import pro.sky.dz11libsnik.exception.EmployeeNotFoundException;
import pro.sky.dz11libsnik.exception.EmployeeStorageIsFullException;
import pro.sky.dz11libsnik.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    private final static int MAX_SIZE = 2;


    @Override
    public Employee add(String firstName, String lastName, double salary, int departmentId) {

        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);


        validateFirstAndLastNames(firstName, lastName);

        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee newEmployee = new Employee(firstName, lastName, salary, departmentId);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }

        employees.add(newEmployee);
        return newEmployee;
    }

    @Override
    public Employee find(String firstName, String lastName, double salary, int departmentId) {
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

        validateFirstAndLastNames(firstName, lastName);

        Employee employeeForFound = new Employee(firstName, lastName, salary, departmentId);
        for (Employee e : employees) {
            if (e.equals(employeeForFound)) {
                return e;
            }
        }

        throw new EmployeeNotFoundException("Такого сотрудника нет");
    }

    @Override
    public Employee remove(String firstName, String lastName, double salary, int departmentId) {
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

        validateFirstAndLastNames(firstName, lastName);
        Employee employeeForRemove = new Employee(firstName, lastName, salary, departmentId);

        boolean removeResult = employees.remove(employeeForRemove);
        if (removeResult) {
            return employeeForRemove;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не удален - не был найден в базе");
        }
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }

    public void validateFirstAndLastNames(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName)) {
            throw new ValidationException("Имя содержит запрещенные символы");
        }

        if (!StringUtils.isAlpha(lastName)) {
            throw new ValidationException("Фамилия содержит запрещенные символы");
        }
    }
}
