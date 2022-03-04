package com.example.petapp20;

public class Users_Form {
    private String First;
    private String Last;
    private String Email;
    private String Password;
    private Address_form Address;
    private Pet_form Pet;
    private appointment_form appointment;

    public Users_Form(String FirstName, String Email, String LastName, String Password) {
        this.Email = Email;
        this.First = FirstName;
        this.Last = LastName;
        this.Password = Password;
        this.Address = new Address_form();
        this.Pet = new Pet_form();
        this.appointment = new appointment_form();
    }

    public Users_Form() {
        this.Address = new Address_form();
        this.Pet = new Pet_form();
        this.appointment = new appointment_form();
        this.Email = "";
        this.First = "";
        this.Last = "";
        this.Password = "";
    }

    public String getFirstName() {
        return First;
    }

    public void setFirstName(String firstName) {
        First = firstName;
    }

    public String getLastName() {
        return Last;
    }

    public void setLastName(String lastName) {
        Last = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Address_form getAddress() {
        return Address;
    }

    public void setAddress(Address_form address) {
        Address = address;
    }

    public Pet_form getPet() {
        return Pet;
    }

    public void setPet(Pet_form pet) {
        Pet = pet;
    }

    public appointment_form getAppointment() {
        return appointment;
    }

    public void setAppointment(appointment_form Appointment) {
        appointment = Appointment;
    }

}
