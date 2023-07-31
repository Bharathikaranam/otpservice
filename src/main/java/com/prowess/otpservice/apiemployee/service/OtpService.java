package com.prowess.otpservice.apiemployee.service;

import com.prowess.otpservice.apiemployee.model.Employee;
import com.prowess.otpservice.apiemployee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OtpService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setEmailId(updatedEmployee.getEmailId());
            existingEmployee.setContactNumber(updatedEmployee.getContactNumber());
            // You can include additional fields if needed
            employeeRepository.save(existingEmployee);
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Implement OTP-related methods

    public boolean generateAndSendOTP(String employeeId) {
        // Implementation of OTP generation and sending logic
        // ...

        // For the sake of example, let's return true to indicate that OTP was sent successfully
        return true;
    }

    public boolean verifyOTP(String employeeId, String otp) {
        // Implementation of OTP verification logic
        // ...

        // For the sake of example, let's return true to indicate that OTP verification was successful
        return true;
    }

    // Method to generate OTP and send it to the provided email address
    public boolean generateAndSendOTPToEmail(String employeeId, String email) {
        // Implementation of OTP generation and sending to the provided email address
        // Replace this example logic with your actual implementation to generate OTP and send it to the email address
        String otp = generateOTP();
        boolean sentSuccessfully = sendOTPByEmail(otp, email);
        return sentSuccessfully;
    }

    // Example helper methods (Replace these with your actual implementation):
    private String generateOTP() {
        // Generate OTP logic...
        return "123456"; // Example OTP, replace with actual generated OTP.
    }

    private boolean sendOTPByEmail(String otp, String email) {
        // Sending OTP to the email logic...
        System.out.println("OTP sent to " + email + ": " + otp);
        return true; // Assuming the OTP is always sent successfully, replace with actual sending logic.
    }
}
