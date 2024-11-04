package com.redis.cache.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EmployeeResponse(long id, String name, String email, LocalDate dateOfBirth,int age) {

}
