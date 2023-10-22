package pro.sky.dz11libsnik.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.dz11libsnik.entity.Employee;
import pro.sky.dz11libsnik.exception.EmployeeNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.dz11libsnik.service.utils.EmployeeGenerator.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void getEmployeeWithMaxSalary_success() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Employee expectedEmployee = getEmployee();

        //Начало теста
        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
        assertTrue(getEmployee().getSalary() > getEmployee2().getSalary());
    }

    @Test
    void getEmployeeWithMaxSalary_withEmployeeNotFoundException() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        String expectedMessage = "404 Сотрудник с максимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMaxSalary(departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getEmployeeWithMinSalary_success() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Employee expectedEmployee = getEmployee2();

        //Начало теста
        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
        assertTrue(getEmployee().getSalary() > getEmployee2().getSalary());
    }

    @Test
    void getEmployeeWithMinSalary_withEmployeeNotFoundException() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        String expectedMessage = "404 Сотрудник с минимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMinSalary(departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getEmployeesByDepartment_withDepartmentId() {
        //Подготовка входных данных
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_DEPARTMENT_ID, Arrays.asList(getEmployee(), getEmployee2()));

        //Начало теста
        Map<Integer, List<Employee>> actualMap = departmentService.getEmployeesByDepartment(departmentId);
        assertEquals(expectedMap, actualMap);
        assertEquals(getEmployee().getDepartmentId(), getEmployee2().getDepartmentId());
        assertNotEquals(getEmployee().getDepartmentId(), getEmployee3().getDepartmentId());
    }

    @Test
    void getEmployeesByDepartment_withoutDepartmentId() {
        //Подготовка входных данных
        Integer departmentId = null;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_DEPARTMENT_ID, Arrays.asList(getEmployee(), getEmployee2()));
        expectedMap.put(SECOND_DEPARTMENT_ID, Collections.singletonList(getEmployee3()));

        //Начало теста
        Map<Integer, List<Employee>> actualMap = departmentService.getEmployeesByDepartment(departmentId);
        assertEquals(expectedMap, actualMap);
        assertEquals(getEmployee().getDepartmentId(), getEmployee2().getDepartmentId());
        assertNotEquals(getEmployee().getDepartmentId(), getEmployee3().getDepartmentId());
    }
}