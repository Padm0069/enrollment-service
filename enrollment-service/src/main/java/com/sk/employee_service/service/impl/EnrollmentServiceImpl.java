package com.sk.employee_service.service.impl;

import com.sk.employee_service.client.CourseServiceClient;
import com.sk.employee_service.dto.EnrollmentDto;
import com.sk.employee_service.dto.CourseDto;
import com.sk.employee_service.entity.Enrollment;
import com.sk.employee_service.exceptions.EnrollmentAlreadyExistsException;
import com.sk.employee_service.exceptions.EnrollmentNotFoundException;
import com.sk.employee_service.mapper.EnrollmentMapper;
import com.sk.employee_service.repository.EnrollmentRepository;
import com.sk.employee_service.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService {

    @Autowired
    private EnrollmentRepository repository;

    @Autowired
    private CourseServiceClient courseServiceClient;

    @Override
    public void createEnrollment(EnrollmentDto enrollmentDto) {
        Optional<Enrollment> enrollmentOptional = repository.findById(enrollmentDto.getId());

        if (enrollmentOptional.isPresent()) {
            throw new EnrollmentAlreadyExistsException("Enrollment already exists with ID - " + enrollmentDto.getId());
        }

        // Fetch course details if necessary
        CourseDto courseDto = courseServiceClient.getCourseById(enrollmentDto.getCourseId());

        // Additional validation or logic with courseDto

        Enrollment enrollment = EnrollmentMapper.mapToEnrollment(enrollmentDto, new Enrollment());
        repository.save(enrollment);
    }

    @Override
    public EnrollmentDto fetchEnrollment(Integer id) {
        Enrollment enrollment = repository.findById(id).orElseThrow(
                () -> new EnrollmentNotFoundException("Enrollment does not exist for ID - " + id)
        );

        return EnrollmentMapper.mapToEnrollmentDto(enrollment, new EnrollmentDto());
    }

    @Override
    public List<EnrollmentDto> fetchAllEnrollments() {
        return repository.findAll().stream()
                .map(enrollment -> EnrollmentMapper.mapToEnrollmentDto(enrollment, new EnrollmentDto()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateEnrollment(EnrollmentDto enrollmentDto) {
        if (enrollmentDto.getId() == null) {
            return false;
        }
        Enrollment enrollment = repository.findById(enrollmentDto.getId()).orElseThrow(
                () -> new EnrollmentNotFoundException("Enrollment does not exist for ID - " + enrollmentDto.getId())
        );

        Enrollment updatedEnrollment = EnrollmentMapper.mapToEnrollment(enrollmentDto, enrollment);
        repository.save(updatedEnrollment);
        return true;
    }

    @Override
    public boolean deleteEnrollment(Integer id) {
        Enrollment enrollment = repository.findById(id).orElseThrow(
                () -> new EnrollmentNotFoundException("Enrollment does not exist for ID - " + id)
        );
        repository.delete(enrollment);
        return true;
    }
}
