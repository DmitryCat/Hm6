package com.example.Collection.Service;

import com.example.Collection.Class.Employee;

import java.util.Map;

public interface EmployeeService {
    Employee add(String firstName, String lastName, Integer salary, Integer department);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Map<String, Employee> getAll();
}
