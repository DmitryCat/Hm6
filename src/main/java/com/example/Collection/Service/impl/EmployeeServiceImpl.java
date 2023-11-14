package com.example.Collection.Service.impl;

import com.example.Collection.Class.Employee;
import com.example.Collection.Exception.EmployeeAlreadyAddedException;
import com.example.Collection.Exception.EmployeeNotFoundException;
import com.example.Collection.Exception.EmployeeStorageIsFullException;
import com.example.Collection.Service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int STORAGE_SIZE = 10;
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= STORAGE_SIZE) {
            throw new EmployeeStorageIsFullException("Хранилище переполнено");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }

        employees.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee requestedEmployee = new Employee(firstName, lastName);
        for (Employee employee : employees) {
            if (employee.equals(requestedEmployee)) {
                return requestedEmployee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }
    @Override
    public List<Employee> getAll() {
        return employees;
    }
}
