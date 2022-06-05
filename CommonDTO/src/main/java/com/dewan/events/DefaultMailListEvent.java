package com.dewan.events;

import com.dewan.dto.DefaultMailListDTO;
import com.dewan.enums.DefaultMailListStatus;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class DefaultMailListEvent implements Event{

    private final UUID eventId = UUID.randomUUID();
    private final Date date = new Date();

    private DefaultMailListDTO usersMailListDTO;
    private DefaultMailListStatus defaultMailListStatus;

    public DefaultMailListEvent(){}

    public DefaultMailListEvent(DefaultMailListDTO usersMailListDTO, DefaultMailListStatus defaultMailListStatus) {
        this.usersMailListDTO = usersMailListDTO;
        this.defaultMailListStatus = defaultMailListStatus;
    }

    public UUID getEventId() {
        return this.eventId;
    }
    public Date getDate() {
        return this.date;
    }

    public DefaultMailListDTO getUsersMailListDTO() {
        return usersMailListDTO;
    }

    public DefaultMailListStatus getDefaultMailListStatus() {
        return defaultMailListStatus;
    }
}
