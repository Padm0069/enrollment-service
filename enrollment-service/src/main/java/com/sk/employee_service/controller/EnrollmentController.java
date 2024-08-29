package com.sk.employee_service.controller;

import com.sk.employee_service.dto.EnrollmentDto;
import com.sk.employee_service.dto.ResponseDto;
import com.sk.employee_service.service.IEnrollmentService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Enrollment Controller",
        description = "Enrollment Controller for CRUD operations",
        externalDocs = @ExternalDocumentation(
                url = "http://www.example.com",
                description = "The external documentation description"
        )
)
@Validated
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private IEnrollmentService enrollmentService;

    @Operation(
            description = "Create new Enrollment",
            summary = "Post API to create a new enrollment"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Success in creating enrollment"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEnrollment(@RequestBody @Valid EnrollmentDto enrollmentDto) {
        enrollmentService.createEnrollment(enrollmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto("Created successfully", "201")
        );
    }

    @Operation(
            description = "Fetch Enrollment by ID",
            summary = "Get API to fetch enrollment details by ID"
    )
    @GetMapping("/fetch")
    public ResponseEntity<EnrollmentDto> fetchEnrollment(@RequestParam @NotNull Integer id) {
        EnrollmentDto enrollmentDto = enrollmentService.fetchEnrollment(id);
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentDto);
    }

    @Operation(
            description = "Fetch all Enrollments",
            summary = "Get API to fetch all enrollments"
    )
    @GetMapping("/fetch-all")
    public ResponseEntity<List<EnrollmentDto>> fetchAllEnrollments() {
        List<EnrollmentDto> enrollmentList = enrollmentService.fetchAllEnrollments();
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentList);
    }

    @Operation(
            description = "Update existing Enrollment",
            summary = "Put API to update enrollment details"
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateEnrollment(@RequestBody @Valid EnrollmentDto enrollmentDto) {
        boolean isUpdated = enrollmentService.updateEnrollment(enrollmentDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("Updated Successfully", "203")
            );
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Not updated", "501")
            );
        }
    }

    @Operation(
            description = "Delete Enrollment by ID",
            summary = "Delete API to remove enrollment by ID"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteEnrollment(@RequestParam @NotNull Integer id) {
        boolean isDeleted = enrollmentService.deleteEnrollment(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("Deleted Successfully", "200")
            );
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Not deleted", "501")
            );
        }
    }

    @GetMapping("/greet")
    public String greet() {
        return "Hello World!";
    }

    @GetMapping("/build-info")
    public String buildInfo() {
        return buildVersion;
    }
}
