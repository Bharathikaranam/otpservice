package com.prowess.otpservice.apiemployee.controller;

import com.prowess.otpservice.apiemployee.model.Employee;
import com.prowess.otpservice.apiemployee.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class OtpServiceController {

    private final OtpService otpService;

    @Autowired
    public OtpServiceController(OtpService otpService) {
        this.otpService = otpService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return otpService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return otpService.getEmployeeById(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return otpService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        otpService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        otpService.deleteEmployee(id);
    }

    // Add the following endpoint mapping for requesting OTP
    @PostMapping("/{employeeId}/request-otp")
    public ResponseEntity<?> requestOTP(@PathVariable String employeeId) {
        // Implementation of OTP request logic
        // This is where you can generate and send OTP to the employee's contact number or email, etc.
        // For the sake of example, let's assume we have a method in the OtpService for OTP generation.

        boolean otpSent = otpService.generateAndSendOTP(employeeId);

        if (otpSent) {
            return new ResponseEntity<>("OTP requested for employee with ID: " + employeeId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to request OTP for employee with ID: " + employeeId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add the following endpoint mapping for sending OTP to the contact number
    @PostMapping("/{employeeId}/send-otp")
    public ResponseEntity<?> sendOTPToContactNumber(@PathVariable String employeeId) {
        // Implementation of OTP sending logic
        // This is where you can generate and send OTP to the employee's contact number using the OtpService.

        boolean otpSent = otpService.generateAndSendOTP(employeeId);

        if (otpSent) {
            return new ResponseEntity<>("OTP sent to the contact number of employee with ID: " + employeeId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to send OTP to the contact number of employee with ID: " + employeeId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Add the following endpoint mapping for verifying OTP
    @PostMapping("/{employeeId}/verify-otp")
    public ResponseEntity<?> verifyOTP(@PathVariable String employeeId, @RequestParam String otp) {
        // Implementation of OTP verification logic
        // This is where you can verify the received OTP against the stored OTP for the employee.
        // For the sake of example, let's assume we have a method in the OtpService for OTP verification.

        boolean otpVerified = otpService.verifyOTP(employeeId, otp);

        if (otpVerified) {
            return new ResponseEntity<>("OTP verified successfully for employee with ID: " + employeeId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("OTP verification failed for employee with ID: " + employeeId, HttpStatus.UNAUTHORIZED);
        }
    }

    // Add the following endpoint mapping for sending OTP to the email address
    @PostMapping("/{employeeId}/send-otp-to-email")
    public ResponseEntity<?> sendOTPToEmail(@PathVariable String employeeId, @RequestParam String email) {
        // Implementation of OTP sending logic
        // This is where you can generate and send OTP to the employee's email address using the OtpService.
        // Make sure you have a method in the OtpService to handle OTP sending to the provided email address.

        boolean otpSent = otpService.generateAndSendOTPToEmail(employeeId, email);

        if (otpSent) {
            return new ResponseEntity<>("OTP sent to the email address of employee with ID: " + employeeId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to send OTP to the email address of employee with ID: " + employeeId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
