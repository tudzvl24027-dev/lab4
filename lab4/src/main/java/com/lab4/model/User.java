package com.lab4.model;

public class User {
    private int id;
    private String fullname;
    private String email;
    private String gender;
    private String major;

    public User() {}

    public User(String fullname, String email, String gender, String major) {
        this.fullname = fullname;
        this.email = email;
        this.gender = gender;
        this.major = major;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
}