package com.example.petapp20;

public class appointment_form {
    private String Date;
    private String Time;

    public appointment_form(String Date, String Time) {
        this.Date = Date;
        this.Time = Time;

    }

    public appointment_form() {
        this.Date = "";
        this.Time = "";

    }

    public appointment_form(appointment_form time) {
        this.Date = time.Date;
        this.Time = time.Time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

}
