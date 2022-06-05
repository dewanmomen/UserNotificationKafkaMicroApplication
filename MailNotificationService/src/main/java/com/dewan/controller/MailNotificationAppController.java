package com.dewan.controller;


import com.dewan.entity.DefaultMailListEntity;
import com.dewan.service.DefaultMailListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/v1")
@Validated
public class MailNotificationAppController {

    private final Logger log = LoggerFactory.getLogger(MailNotificationAppController.class);

    @Autowired
    private DefaultMailListService defaultMailListService;


    //@RequestMapping(value = "/users_mail_list",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/users_mail_list/{user_id}")
    @ResponseBody
    public ResponseEntity<Object> getUsersMailList(@PathVariable String user_id)
    {
        log.info("User email list api called");
        long userId = Long.parseLong(user_id);
        log.info("get user_id :"+userId);
        DefaultMailListEntity defaultMailListEntity =  defaultMailListService.getUsersMailList(userId);
        if (defaultMailListEntity != null) {
            return new ResponseEntity<>(defaultMailListEntity, HttpStatus.OK);
        }
        return new ResponseEntity<>(defaultMailListEntity, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/getAllMailList")
    public List<DefaultMailListEntity> getAllMailList(){
        return this.defaultMailListService.getAllMailList();
    }
}
