package pro.sky.dz11libsnik.service;

import pro.sky.dz11libsnik.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastName, double salary, int departmentId);

    Employee find(String firstName, String lastName, double salary, int departmentId);

    Employee remove(String firstName, String lastName, double salary, int departmentId);

    List<Employee> getAll();
}
