package model.entities;

public class Person {
    protected int id;
    protected String firstName;
    protected String secondName;
    protected String phoneNumber; 
    protected String email;

    public Person(int id, String firstName, String secondName, String phoneNumber, String email) {
        this.id = id; 
        this.firstName = firstName; 
        this.secondName = secondName; 
        this.phoneNumber = phoneNumber; 
        this.email = email;
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
}
