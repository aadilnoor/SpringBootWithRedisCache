package com.redis.cache.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.redis.cache.entity.Employee;
import com.redis.cache.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse saveEmployee(Employee employee) throws JsonProcessingException;
    EmployeeResponse getEmployeeById(Long id) throws JsonProcessingException;

    String deleteRecordById(Long id);

    String deleteByMultpleIds(List<Long> ids);
}
