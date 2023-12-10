package com.example.Collection.Service.impl;

import com.example.Collection.Class.Employee;
import com.example.Collection.Exception.EmployeeAlreadyAddedException;
import com.example.Collection.Exception.EmployeeNotFoundException;
import com.example.Collection.Exception.EmployeeStorageIsFullException;
import com.example.Collection.Exception.InvalidNameException;
import com.example.Collection.Service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
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
    public Map<String, Employee> getAll() {
        return Collections.unmodifiableMap(employees);
    }
    @Override
    public Employee add(String firstName, String lastName, Integer salary, Integer department) {
        validateNames(firstName, lastName);
        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        Employee employee = new Employee(StringUtils.capitalize(firstName),
                StringUtils.capitalize(lastName),
                salary,
                department);
        employees.put(getKey(firstName, lastName), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        validateNames(firstName, lastName);
        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.remove(getKey(firstName, lastName));
    }

    @Override
    public Employee find(String firstName, String lastName) {
        validateNames(firstName, lastName);
        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.get(getKey(firstName, lastName));
    }


    private void validateNames(String... names) {
        for (String name : names) {
            if (!StringUtils.isAlpha(name)) {
                throw new InvalidNameException(name + "это ошибка");
            }
        }
    }


    private static String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }
    private static String getKey(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }
}
