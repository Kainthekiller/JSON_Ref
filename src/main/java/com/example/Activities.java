package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;

public class Activities {

    User user;
    Status status;



    //Getters && Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


// USER CLASS
class User {

    int id;
    String username;
    ArrayList<Email> emails;

    public User()
    {

    }

    //Getter && Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    //public ArrayList<Email> getEmails() {
//        return emails;
//    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }
}

class Email {

    int id;
    String address;
    boolean primary;

    public Email()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}


class Status {

    String text;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    String date;

    public Status()
    {

    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

class Views {

    interface CompactView {};
    interface DetailedView extends CompactView {};
}
