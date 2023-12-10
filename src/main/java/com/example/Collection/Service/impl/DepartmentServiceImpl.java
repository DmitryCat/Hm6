package com.example.Collection.Service.impl;

import com.example.Collection.Class.Employee;
import com.example.Collection.Exception.InvalidNameException;
import com.example.Collection.Service.DepartmentService;
import com.example.Collection.Service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public List<Employee> getEmployeeByDepartment(Integer departmentId) {
        return employeeService.getAll().values()
                .stream()
                .filter(employee -> departmentId.equals(employee.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getEmployees() {
        return employeeService.getAll().values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
