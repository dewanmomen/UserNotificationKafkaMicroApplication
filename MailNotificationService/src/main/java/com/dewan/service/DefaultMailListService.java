package com.dewan.service;

import com.dewan.dto.DefaultMailListDTO;
import com.dewan.entity.DefaultMailListEntity;
import com.dewan.enums.UserRegistrationStatus;
import com.dewan.events.DefaultMailListEvent;
import com.dewan.events.UserRegistrationEvent;
import com.dewan.repository.DefaultMailListRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import java.util.List;

import static com.dewan.enums.DefaultMailListStatus.APPROVED;
import static com.dewan.enums.DefaultMailListStatus.DECLINED;

@Service
public class DefaultMailListService{

    private final Logger log = LoggerFactory.getLogger(DefaultMailListService.class);
    @Autowired
    DefaultMailListRepository defaultMailListRepository;

    @Transactional
    public DefaultMailListEvent handleEvent(UserRegistrationEvent event)
    {
        log.info("handleEvent method called form DefaultMailListService Object");
        long userid = event.getUserRegistrationDto().getUser_id();
        String email = event.getUserRegistrationDto().getEmail();
        DefaultMailListDTO dto = DefaultMailListDTO.of(userid,email);

        DefaultMailListEvent defaultMailListEvent = new DefaultMailListEvent();
        defaultMailListEvent.setUsersMailListDTO(dto);

        String searchDomain  = "@gmail.com";
        if ( email.toLowerCase().indexOf(searchDomain.toLowerCase()) != -1 ) {
            log.info("I found the @google.com domain name");
            defaultMailListEvent.setDefaultMailListStatus(DECLINED);
            event.setUserRegistrationStatus(UserRegistrationStatus.FAILED);
        } else {
            // if '@gmail.com' does not match, otherwise true case
            //log.info("not found @google.com domain name");
            defaultMailListEvent.setDefaultMailListStatus( APPROVED );
            DefaultMailListEntity defaultMailListEntity = new DefaultMailListEntity();
            defaultMailListEntity.setUserid(userid);
            defaultMailListEntity.setEmail(email);

            defaultMailListRepository.save(defaultMailListEntity);
        }
        return  defaultMailListEvent;
    }

    @org.springframework.transaction.annotation.Transactional
    public void cancelEvent(UserRegistrationEvent userRegistrationEvent){
        DefaultMailListEntity defaultMailListEntity = this.defaultMailListRepository.findDefaultMailListEntityByUserid(userRegistrationEvent.getUserRegistrationDto().getUser_id());
        if(defaultMailListEntity != null)
            this.defaultMailListRepository.delete(defaultMailListEntity);
    }

    @Transactional
    public List<DefaultMailListEntity> getAllMailList() {
        return this.defaultMailListRepository.findAll();
    }

    public DefaultMailListEntity getUsersMailList(long userId) {
        DefaultMailListEntity defaultMailListEntity = this.defaultMailListRepository.findDefaultMailListEntityByUserid(userId);
//        if(defaultMailListEntity != null)
//            return defaultMailListEntity;
//        else
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "Data not found"
//            );
        return defaultMailListEntity;
    }

}
