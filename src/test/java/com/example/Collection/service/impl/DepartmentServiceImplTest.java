package com.example.Collection.service.impl;

import com.example.Collection.Class.Employee;
import com.example.Collection.Service.impl.DepartmentServiceImpl;
import com.example.Collection.Service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;
    private final List<Employee> employee = new ArrayList<>() {{
        add(new Employee("Dmitry", "Ilin", 50000, 1));
        add(new Employee("Ivan", "Ivanov", 60000, 1));
        add(new Employee("Maxim", "Pavlov", 55000, 1));
        add(new Employee("Julia", "Antonov", 70000, 2));
        add(new Employee("Kirill", "Bogov", 90000, 2));
        add(new Employee("Anton", "Kotlin", 10000, 3));
    }};

    @Test
    public void shouldCalculateSum() {
        int departmentId = 1;
        int expectedSum = 165000;
        Map<String, Employee> employeeMap = new HashMap<>() {{
            put(employee.get(0).getFirstName() + employee.get(0).getLastName(), employee.get(0));
            put(employee.get(1).getFirstName() + employee.get(1).getLastName(), employee.get(1));
            put(employee.get(2).getFirstName() + employee.get(2).getLastName(), employee.get(2));
        }};
        Mockito.when(employeeService.getAll()).thenReturn(employeeMap);
        Integer salarySum = departmentService.getSalarySum(departmentId);
        Assertions.assertEquals(expectedSum, salarySum);
    }

    @Test
    public void shouldFindMinSalary() {
        int departmentId = 1;
        Employee expectedSalary = employee.get(0);
        Map<String, Employee> employeeMap = new HashMap<>() {{
            put(employee.get(0).getFirstName() + employee.get(0).getLastName(), employee.get(0));
            put(employee.get(1).getFirstName() + employee.get(1).getLastName(), employee.get(1));
            put(employee.get(2).getFirstName() + employee.get(2).getLastName(), employee.get(2));
        }};
        Mockito.when(employeeService.getAll()).thenReturn(employeeMap);
        Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);
        Assertions.assertEquals(expectedSalary, employee);
    }

    @Test
    public void shouldFindMaxSalary() {
        int departmentId = 1;
        Employee expectedSalary = employee.get(1);
        Map<String, Employee> employeeMap = new HashMap<>() {{
            put(employee.get(0).getFirstName() + employee.get(0).getLastName(), employee.get(0));
            put(employee.get(1).getFirstName() + employee.get(1).getLastName(), employee.get(1));
            put(employee.get(2).getFirstName() + employee.get(2).getLastName(), employee.get(2));
        }};
        Mockito.when(employeeService.getAll()).thenReturn(employeeMap);
        Employee employee = departmentService.getEmployeeWithMaxSalary(departmentId);
        Assertions.assertEquals(expectedSalary, employee);
    }

    @Test
    public void shouldFindEmployeeByDepartment() {
        int departmentId = 1;
        List<Employee> expectedEmployees = new ArrayList<>() {{
            add(employee.get(0));
            add(employee.get(1));
            add(employee.get(2));
        }};
        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employee) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }
        when(employeeService.getAll()).thenReturn(employeeMap);
        List<Employee> actualEmployees = departmentService.getEmployees(departmentId);
        Assertions.assertTrue(actualEmployees.containsAll(expectedEmployees) && expectedEmployees.containsAll(actualEmployees));
    }

    @Test
    public void shouldReturnNullWhenThenAreNoEmployees() {
        int departmentId = 1;
        when(employeeService.getAll()).thenReturn(Collections.emptyMap());
        Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);
        Assertions.assertNull(employee);
    }

    @Test
    public void shouldCorrectlyGroupEmployeeBuDepartmentId() {
        Map<String, Employee> employeeMap = new HashMap<>();
        for (Employee employee : employee) {
            employeeMap.put(employee.getFirstName() + employee.getLastName(), employee);
        }
        when(employeeService.getAll()).thenReturn(employeeMap);
        Map<Integer, List<Employee>> expectedMap = new HashMap<>() {{
            put(1, List.of(employee.get(0), employee.get(1), employee.get(2)));
            put(2, List.of(employee.get(3), employee.get(4)));
            put(3, List.of(employee.get(5)));
        }};
        Map<Integer, List<Employee>> actualMap = departmentService.getGroupByDepartmentEmployees();
        Assertions.assertEquals(expectedMap, actualMap);
    }
}
