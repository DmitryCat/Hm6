package com.example.Collection.service.impl;

import com.example.Collection.Class.Employee;
import com.example.Collection.Exception.EmployeeAlreadyAddedException;
import com.example.Collection.Service.EmployeeService;
import com.example.Collection.Service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeServiceImplTest {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void shouldThrowEmployeeAlreadyAddedExceptionWhenEmployeeIsAlreadyExisted() {
        Employee employee = new Employee("Dmitry", "Ilin", 22111, 1);
        employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        });
    }

    @Test
    public void shouldCorrectlyFindEmployee() {
        Employee employee = new Employee("Dmitry", "Ilin", 22111, 1);
        employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartment());
        Employee actualEmployee = employeeService.find(employee.getFirstName(), employee.getLastName());
        Assertions.assertEquals(employee, actualEmployee);
    }
}
