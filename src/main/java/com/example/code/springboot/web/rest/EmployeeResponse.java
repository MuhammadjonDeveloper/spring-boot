package com.example.code.springboot.web.rest;

import com.example.code.springboot.domain.Employee;
import com.example.code.springboot.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeResponse {
    private EmployeeService employeeService;

    public EmployeeResponse(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity create(@RequestBody Employee employee){
        Employee employee1= employeeService.save(employee);
        return ResponseEntity.ok(employee1);
    }
    @PutMapping("/employee")
    public ResponseEntity update(@RequestBody Employee employee){
        Employee employee1= employeeService.save(employee);
        return ResponseEntity.ok(employee1);
    }

    @GetMapping("/employees")
    public ResponseEntity getAll(){
        List<Employee> employeeList= employeeService.getAll();
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("/employees/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        List<Employee> employeeList=employeeService.findAllByLike(name);
        return ResponseEntity.ok(employeeList);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity findByName(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok("Ma'lumot o'chirildi");
    }
}
