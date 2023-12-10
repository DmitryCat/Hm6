package com.example.Collection.Service.impl;

import com.example.Collection.Class.Employee;
import com.example.Collection.Exception.EmployeeAlreadyAddedException;
import com.example.Collection.Exception.EmployeeNotFoundException;
import com.example.Collection.Exception.EmployeeStorageIsFullException;
import com.example.Collection.Service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int STORAGE_SIZE = 10;
    @PostConstruct
    public void addEmployees() {
        add("Dmitry", "Cat", 85000, 1);
        add("Maksim", "Dog", 35000, 1);
        add("Ilya", "Duck", 45000, 1);
        add("Kolya", "Bird", 75000, 2);
        add("Nastya", "Bird", 95000, 2);
        add("Yulia", "Bird", 775000, 3);
    }
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee add(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() >= STORAGE_SIZE) {
            throw new EmployeeStorageIsFullException("Хранилище переполнено");
        }

        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }
        Employee employee = new Employee(firstName, lastName, salary, department);
        employees.put(getKey(employee), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.remove(getKey(firstName, lastName));
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(getKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    @Override
    public Map<String, Employee> getAll() {
        return Collections.unmodifiableMap(employees);
    }

    private static String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    private static String getKey(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }
}
