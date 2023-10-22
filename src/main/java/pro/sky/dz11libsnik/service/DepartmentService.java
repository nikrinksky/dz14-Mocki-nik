package pro.sky.dz11libsnik.service;

import pro.sky.dz11libsnik.entity.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalary(Integer departmentId);

    Employee getEmployeeWithMinSalary(Integer departmentId);

    Map<Integer, List<Employee>> getEmployeesByDepartment(Integer departmentId);
}
