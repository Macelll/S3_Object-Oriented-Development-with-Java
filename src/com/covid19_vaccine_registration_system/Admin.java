package com.covid19_vaccine_registration_system;

public class Admin{

    private String username;
    private String password;
    private Gender gender;
    private int age;

    public Admin(String username, String password, Gender gender, int age) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
