package com.prowess.otpservice.controller;

import com.prowess.otpservice.model.Employee;
import com.prowess.otpservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;

@RestController
@RequestMapping("/api/v1")
public class OtpServiceController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        // Generate 4-digit OTP
        String otp = generateOTP();

        // Set the OTP in the employee object
        employee.setOtp(otp);

        // Save the employee to the database
        return employeeRepository.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeData) {
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

    @GetMapping("/sendOTP/{id}")
    public String sendOTP(@PathVariable Long id) throws MessagingException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return "Employee not found";
        }

        // Generate 4-digit OTP
        String otp = generateOTP();

        // Update the OTP in the employee object and save it to the database
        employee.setOtp(otp);
        employeeRepository.save(employee);

        // Send OTP via email
        sendOTPEmail(employee.getEmailId(), otp);

        // Send OTP via SMS (You need to implement this part using an SMS gateway)

        return "OTP sent successfully";
    }

    @GetMapping("/sendEmail")
    public String sendEmail() {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // Pass 'true' for multipart
            helper.setTo("karanambharathi123@gmail.com"); // Replace with the recipient's email address
            helper.setSubject("Test Email");
            helper.setText("This is a test email sent from Spring Boot.");

            mailSender.send(message);
            return "Email sent successfully!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send email.";
        }
    }

    // Method to add an employee with specified details
    @GetMapping("/addDummyEmployee")
    public Employee addDummyEmployee() {
        Employee employee = new Employee("John Doe", "EMP123", "john.doe@example.com", new String[]{"1234567890", "9876543210"});
        return addEmployee(employee);
    }

    private String generateOTP() {
        Random random = new Random();
        int otpValue = 1000 + random.nextInt(9000);
        return String.valueOf(otpValue);
    }

    private void sendOTPEmail(String email, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true); // Pass 'true' for multipart
        try {
            helper.setTo(email);
            helper.setSubject("OTP Verification");
            helper.setText("Your OTP is: " + otp);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
