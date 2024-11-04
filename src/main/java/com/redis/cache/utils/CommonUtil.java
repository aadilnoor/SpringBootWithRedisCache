package com.redis.cache.utils;

import com.redis.cache.entity.Employee;
import com.redis.cache.response.EmployeeResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CommonUtil {
    public static int calculateAge(String dob) {
        LocalDate birthDate = null;
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            birthDate = LocalDate.parse(dob, dateTimeFormatter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    public static EmployeeResponse entityToEmployeeResponse(Employee employee) {
        if (employee == null) {
            return null;
        }
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .dateOfBirth(employee.getDateOfBirth())
                .age(employee.getAge())
                .build();

    }
}
