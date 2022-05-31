package com.dewan.event;

import lombok.Data;

@Data
public class UserRegistrationEvent implements Event {

    private static final String EVENT = "UserRegistration";

    private Long userid;
    private String username;
    private String password;
    private String email;

    public UserRegistrationEvent setUserid(Long userid) {
        this.userid = userid;
        return this;
    }

    public UserRegistrationEvent setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRegistrationEvent setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRegistrationEvent setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String getEvent() {
        return EVENT;
    }
}
