package com.prowess.otpservice.model;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "employee1")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "employee_id", unique = true)
    private String employeeId;

    @Column(name = "email_id", unique = true)
    private String emailId;

    @Column(name = "contact_number") // This column type should be an array of strings in the database
    private String[] contactNumber;  // Change the data type to String[]

    private String otp;

    // Constructors (you can create constructors if needed)

    public Employee() {
    }

    public Employee(String name, String employeeId, String emailId, String[] contactNumber) {
        this.name = name;
        this.employeeId = employeeId;
        this.emailId = emailId;
        this.contactNumber = contactNumber;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String[] getContactNumber() { // Change the return type to String[]
        return contactNumber;
    }

    public void setContactNumber(String[] contactNumber) { // Change the parameter type to String[]
        this.contactNumber = contactNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    // Additional methods (if needed)

    // Don't forget to implement the toString() method for debugging purposes
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", contactNumber=" + Arrays.toString(contactNumber) + // Convert array to string representation
                ", otp='" + otp + '\'' +
                '}';
    }
}
