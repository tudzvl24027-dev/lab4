package com.lab4.model;

public class Employee {
    private String empCode;
    private String fullName;
    private String email;

    public Employee() {}

    public Employee(String empCode, String fullName, String email) {
        this.empCode = empCode;
        this.fullName = fullName;
        this.email = email;
    }

    // Getters and Setters
    public String getEmpCode() { return empCode; }
    public void setEmpCode(String empCode) { this.empCode = empCode; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}