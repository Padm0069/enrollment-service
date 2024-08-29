package com.sk.employee_service.service;

import com.sk.employee_service.dto.CourseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CourseServiceClient {

    @Value("${course.service.url}")
    private String courseServiceUrl;

    private final RestTemplate restTemplate;

    public CourseServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CourseDto getCourseById(Integer employeeId) {
        return restTemplate.getForObject(courseServiceUrl + "/api/courses/fetch?employeeId=" + employeeId, CourseDto.class);
    }
}
