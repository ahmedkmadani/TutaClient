package com.tuta.tutadriver.model;

public class User {
    private int user_id;
    private String name, email, phone_number, user_type;

    public User(int user_id, String name, String email, String phone_number, String user_type) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.user_type = user_type;
    }

    public int getUserId() {
        return user_id;
    }

    public String getUsername() {
        return name;
    }

    public String getUserEmail() {
        return phone_number;
    }

    public String getUserPhone() {
        return phone_number;
    }

    public String getUserType() {
        return user_type;
    }

}