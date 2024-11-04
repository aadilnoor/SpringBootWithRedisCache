package com.redis.cache.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.redis.cache.entity.Employee;
import com.redis.cache.repository.EmployeeRepository;
import com.redis.cache.response.EmployeeResponse;
import com.redis.cache.service.EmployeeService;
import com.redis.cache.utils.CommonUtil;
import com.redis.cache.utils.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class EmployeeServiceimpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RedisService redisService;
    private static final String EMPLOYEE_KEY = "EMP_";

    @Override
    public EmployeeResponse saveEmployee(Employee employee) throws JsonProcessingException {
        employee.setAge(CommonUtil.calculateAge(employee.getDateOfBirth().toString()));
        Employee savedEmployee = employeeRepository.save(employee);
        redisService.setValueByKey(EMPLOYEE_KEY + savedEmployee.getId().toString(), savedEmployee, 3000l);
        return CommonUtil.entityToEmployeeResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) throws JsonProcessingException {
        Employee employee = redisService.getValueByKey(EMPLOYEE_KEY + id.toString(), Employee.class);
        if (employee != null) {
            return CommonUtil.entityToEmployeeResponse(employee);
        }
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.map(CommonUtil::entityToEmployeeResponse).orElse(null);
    }

    @Override
    public String deleteRecordById(Long id) {
        boolean isDeleted = redisService.deleteFromRedisCache(EMPLOYEE_KEY + id.toString());
        return isDeleted ? id.toString() + " Is deleted Successfully" : " Key Not found !";
    }

    @Override
    public String deleteByMultpleIds(List<Long> ids) {
        List<String> listOfKeys = ids.stream().map(id -> EMPLOYEE_KEY + id).toList();
        Long deletedCount = redisService.deleteMultiRecordFromRedisCache(listOfKeys);
        return deletedCount > 0 ? deletedCount.toString() + " Records  deleted Successfully" : " Key Not found !";
    }
}
