package com.dewan.event;

import com.dewan.enums.DefaultMailListStatus;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class DefaultMailListEvent implements Event {

    private static final String EVENT = "DefaultMailList";

    private Long userid;
    private String email;

    public DefaultMailListEvent setStatus(DefaultMailListStatus status) {
        this.status = status;
        return this;
    }

    private DefaultMailListStatus status;

    public DefaultMailListEvent setUserid(Long userid) {
        this.userid = userid;
        return this;
    }

    public DefaultMailListEvent setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String getEvent() {
        return EVENT;
    }
}
