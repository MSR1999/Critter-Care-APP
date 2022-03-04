package com.example.petapp20;

public class Pet_form {

    private String Pet_name;
    private String Gender;
    private String Pet_Color;
    private String Breed;
    private String Age;

    public Pet_form(String Name, String Gender, String Color, String Breed, String Age) {
        this.Pet_name = Name;
        this.Gender = Gender;
        this.Pet_Color = Color;
        this.Breed = Breed;
        this.Age = Age;
    }

    public Pet_form() {
        this.Pet_name = "";
        this.Gender = "";
        this.Pet_Color = "";
        this.Breed = "";
        this.Age = "";
    }

    public Pet_form(Pet_form pet) {
        this.Pet_name = pet.Pet_name;
        this.Gender = pet.Gender;
        this.Pet_Color = pet.Pet_Color;
        this.Breed = pet.Breed;
        this.Age = pet.Age;
    }

    public String getPet_name() {
        return Pet_name;
    }

    public void setPet_name(String name) {
        Pet_name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPet_Color() {
        return Pet_Color;
    }

    public void setPet_Color(String color) {
        Pet_Color = color;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }


}
