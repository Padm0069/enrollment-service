package com.sk.employee_service.repository;

import com.sk.employee_service.entity.Enrollment;
import com.sk.employee_service.entity.Enrollment;
import com.sk.employee_service.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    Optional<Enrollment> findByEmployeeId(Integer employeeId);

    void deleteByEmployeeId(Integer employeeId);
}
