package com.ikiu.loginjfx;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private String birthday;
    private String gender;
    private String civilStatus;
    private String country;
    private String contact;
    private String email;
    private String username;
    private String password;
    private String role;

    // Constructors
    public User() {
    }

    public User(String firstName, String lastName, int age, String birthday, String gender,
                String civilStatus, String country, String contact, String email,
                String username, String password , String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthday = birthday;
        this.gender = gender;
        this.civilStatus = civilStatus;
        this.country = country;
        this.contact = contact;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and setters for all fields

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public String getRole(){
        return role;
    }
    void setRole(String role){
        this.role = role;
    }

    @Override
    public String toString() {
        return firstName + "," + lastName + "," + age + "," + birthday + "," +
                gender + "," + civilStatus + "," + country + "," + contact + "," +
                email + "," + username + "," + password + "," + role;
    }
}

