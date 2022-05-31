package com.dewan.eventhandler;


import com.dewan.entity.DefaultMailListEntity;
import com.dewan.event.DefaultMailListEvent;
import com.dewan.event.UserRegistrationEvent;
import com.dewan.repository.DefaultMailListRepository;
import com.dewan.service.DefaultMailListService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static com.dewan.enums.DefaultMailListStatus.APPROVED;
import static com.dewan.enums.DefaultMailListStatus.DECLINED;

@Component
public class UserRegistrationEventHandler implements EventHandler<UserRegistrationEvent, DefaultMailListEvent> {

    //private final DefaultMailListRepository defaultMailListRepository;

    private DefaultMailListService defaultMailListService;

    public UserRegistrationEventHandler(DefaultMailListService defaultMailListService)
    {
        this.defaultMailListService = defaultMailListService;
    }

    @Transactional
    public DefaultMailListEvent handleEvent(UserRegistrationEvent event)
    {
        long userid = event.getUserid();
        String email = event.getEmail();
        DefaultMailListEvent defaultMailListEvent = new DefaultMailListEvent()
                .setUserid(event.getUserid())
                .setEmail(event.getEmail())
                .setStatus( DECLINED ); // initial status will be declined

        if( email.matches("@gmail.com") == false) // if '@gmail.com' does not match, otherwise true case
        {
            defaultMailListEvent.setStatus( APPROVED );
            DefaultMailListEntity defaultMailListEntity = new DefaultMailListEntity();
            defaultMailListEntity.setUserid(userid);
            defaultMailListEntity.setEmail(email);

            defaultMailListService.saveDefaultMailList(defaultMailListEntity);
        }
        return  defaultMailListEvent;
    }
}
