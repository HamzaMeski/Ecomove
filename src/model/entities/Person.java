package model.entities;

public class Person {
    protected int id;
    protected String firstName;
    protected String secondName;
    protected String phoneNumber;
    protected String email;
    protected String password;

    public Person(int id, String firstName, String secondName, String phoneNumber, String email, String password) {
        this.id = id; 
        this.firstName = firstName; 
        this.secondName = secondName; 
        this.phoneNumber = phoneNumber; 
        this.email = email;
        this.password = password;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
