package com.example.Collection.Service;

import com.example.Collection.Class.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getEmployeeWithMaxSalary(Integer departmentId);

    Employee getEmployeeWithMinSalary(Integer departmentId);

    List<Employee> getEmployeeByDepartment(Integer departmentId);

    Map<Integer, List<Employee>> getEmployees();
}
