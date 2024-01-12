package com.example.Collection.Controller;

import com.example.Collection.Class.Employee;
import com.example.Collection.Service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/{id}/salary/sum")
    public Integer getSalarySum(@PathVariable("id") Integer id) {

        return departmentService.getSalarySum(id);
    }

    @GetMapping("/{id}/salary/min")
    public Integer getEmployeeWithMinSalary(@RequestParam("id") Integer id) {

        return departmentService.getEmployeeWithMinSalary(id).getSalary();
    }

    @GetMapping("/{id}/salary/max")
    public Integer getEmployeeWithMaxSalary(@PathVariable("id") Integer id) {

        return departmentService.getEmployeeWithMaxSalary(id).getSalary();
    }
    @GetMapping("/{id}/employee")
    public List<Employee> getEmployees(@PathVariable("id") Integer id) {
        return departmentService.getEmployees(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getGroupByDepartmentEmployees() {

        return departmentService.getGroupByDepartmentEmployees();
    }
}
