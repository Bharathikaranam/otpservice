package com.prowess.otpservice.service;



import com.prowess.otpservice.model.Employee;
import com.prowess.otpservice.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Generate and set OTP for the employee
    private void generateAndSetOTP(Employee employee) {
        String otp = generateOTP();
        employee.setOtp(otp);
    }

    // Save the employee to the database
    public Employee addEmployee(Employee employee) {
        generateAndSetOTP(employee);
        return employeeRepository.save(employee);
    }

    // Get an employee by ID
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElse(null);
    }

    // Update an existing employee
    public Employee updateEmployee(Long id, Employee employeeData) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setName(employeeData.getName());
            employee.setEmployeeId(employeeData.getEmployeeId());
            employee.setEmailId(employeeData.getEmailId());
            employee.setContactNumber(employeeData.getContactNumber());
            return employeeRepository.save(employee);
        }
        return null;
    }

    // Generate a 4-digit OTP
    private String generateOTP() {
        Random random = new Random();
        int otpValue = 1000 + random.nextInt(9000);
        return String.valueOf(otpValue);
    }

    // Send OTP via email
    public void sendOTPEmail(Employee employee) {
        // Code for sending the OTP via email using JavaMailSender (similar to the code in the EmployeeController)
        // ...
    }

    // Additional methods and business logic (if needed)

}

