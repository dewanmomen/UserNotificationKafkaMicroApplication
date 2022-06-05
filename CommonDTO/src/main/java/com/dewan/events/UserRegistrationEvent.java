package com.dewan.events;

import com.dewan.dto.UserRegistrationDto;
import com.dewan.enums.UserRegistrationStatus;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Data
public class UserRegistrationEvent implements Event {

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();

    private UserRegistrationDto userRegistrationDto;
    private UserRegistrationStatus userRegistrationStatus;

    public UserRegistrationEvent() {
    }

    public UserRegistrationEvent(UserRegistrationDto userRegistrationDto, UserRegistrationStatus userRegistrationStatus) {
        this.userRegistrationDto = userRegistrationDto;
        this.userRegistrationStatus = userRegistrationStatus;
    }

    public UUID getEventId() {
        return this.eventId;
    }
    public Date getDate() {
        return this.date;
    }

    public UserRegistrationDto getUserRegistrationDto() {
        return userRegistrationDto;
    }

    public UserRegistrationStatus getUserRegistrationStatus() {
        return userRegistrationStatus;
    }
}
