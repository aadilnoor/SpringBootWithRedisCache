package com.redis.cache.controller;

import com.redis.cache.entity.Employee;
import com.redis.cache.response.EmployeeResponse;
import com.redis.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<EmployeeResponse> saveEmployee(@RequestBody Employee employee) {
        // this is use globally
        EmployeeResponse employeeResponse;
        try {
            employeeResponse = employeeService.saveEmployee(employee);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long id) {
        EmployeeResponse employeeResponse;
        try {
            employeeResponse = employeeService.getEmployeeById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);

    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteRecordById(@PathVariable Long id){
         return employeeService.deleteRecordById(id);
    }

    @DeleteMapping("/deleteByMultiId")
    public String deleteByMultiId(@RequestBody List<Long> ids){
        return employeeService.deleteByMultpleIds(ids);
    }
}
