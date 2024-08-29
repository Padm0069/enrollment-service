package com.sk.employee_service.service;

import com.sk.employee_service.dto.EnrollmentDto;
import java.util.List;

public interface IEnrollmentService {
    void createEnrollment(EnrollmentDto enrollmentDto);
    EnrollmentDto fetchEnrollment(Integer id);
    List<EnrollmentDto> fetchAllEnrollments();
    boolean updateEnrollment(EnrollmentDto enrollmentDto);
    boolean deleteEnrollment(Integer id);
}
